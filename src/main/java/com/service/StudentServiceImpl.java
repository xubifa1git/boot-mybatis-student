package com.service;

import com.mapper.IStudentMapper;
import com.po.Clazz;
import com.po.PageBean;
import com.po.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class StudentServiceImpl implements IStudentService {
    @Autowired
    private IStudentMapper iStudentMapper;
    @Override
    public boolean save(Student student) {
        int flag=iStudentMapper.save(student);
        if (flag>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean delById(Integer sid) {
        int flag=iStudentMapper.delById(sid);
        if (flag>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean update(Student student) {
        int flag=iStudentMapper.update(student);
        if (flag>0){
            return true;
        }
        return false;
    }

    @Override
    public Student findById(Integer sid) {
        return iStudentMapper.findById(sid);
    }

    @Override
    public List<Student> findPageAll(PageBean pageBean) {
        return iStudentMapper.findPageAll(pageBean);
    }

    @Override
    public int rowsAcount() {
        return iStudentMapper.rowsAcount();
    }

    @Override
    public List<Clazz> doinit() {
        return iStudentMapper.doinit();
    }
}
