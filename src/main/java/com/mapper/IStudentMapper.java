package com.mapper;

import com.po.Clazz;
import com.po.PageBean;
import com.po.Student;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IStudentMapper {
    public int save(Student student);
    public int delById(Integer sid);
    public int update(Student student);
    public Student findById(Integer sid);
    public List<Student> findPageAll(PageBean pageBean);
    public int rowsAcount();
    public List<Clazz> doinit();
}
