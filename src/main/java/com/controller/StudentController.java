package com.controller;

import com.po.Clazz;
import com.po.PageBean;
import com.po.Student;
import com.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class StudentController {
    @Autowired
    private IStudentService iStudentService;
    @RequestMapping(value="save_student.do",method = RequestMethod.POST)
    public String save(HttpServletRequest request, HttpServletResponse response, Student student){
        boolean flag=iStudentService.save(student);
        if (flag){
            return "1";
        }
        return "0";
    }
    @RequestMapping(value="delById_student.do",method = RequestMethod.POST)
    public String delById(HttpServletRequest request,HttpServletResponse response,Integer sid){
        boolean flag=iStudentService.delById(sid);
        if (flag){
            return "1";
        }
        return "0";
    }
    @RequestMapping(value = "update_student.do",method = RequestMethod.POST)
    public String update(HttpServletRequest request,HttpServletResponse response,Student student){
        boolean flag=iStudentService.update(student);
        if(flag){
            return "1";
        }
        return "0";
    }
    @RequestMapping(value = "findById_student.do",method = RequestMethod.POST)
    public Student findById(HttpServletRequest request,HttpServletResponse response,Integer sid){
        Student student=iStudentService.findById(sid);
        return student;
    }
    @RequestMapping(value="findPageAll_student.do",method = RequestMethod.POST)
    public Map findPageAll(HttpServletRequest request,HttpServletResponse response,Integer page,Integer rows){
        PageBean pb =  new PageBean();
        page = page == null || page < 1 ? pb.getPage() : page;
        rows = rows == null || rows < 1 ? pb.getRows() : rows;
        // 获取总行数
        int maxRows=iStudentService.rowsAcount();
        int maxPage=0;//总页数
        if(maxRows==0){
            maxPage=1;
        }else{
            maxPage=maxRows%rows==0?maxRows/rows:maxRows/rows+1;
        }
        page=page>maxPage?maxPage:page;
        //获取学生集合
        pb.setPage(page);
        pb.setRows(rows);
        List<Student> lsst = iStudentService.findPageAll(pb);
        //按照easyUI的格式封装数据
        Map<String,Object> map= new HashMap<String,Object>();
        map.put("page", page);
        map.put("rows", lsst);
        map.put("total", maxRows);
        return map;
    }
    @RequestMapping(value="doinit_student.do",method = RequestMethod.GET)
    public List<Clazz> doinit(HttpServletResponse response,HttpServletRequest request){
        List<Clazz> lsca=iStudentService.doinit();
        return lsca;
    }
}
