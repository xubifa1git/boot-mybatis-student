package com.service;

import com.po.Clazz;
import com.po.PageBean;
import com.po.Student;

import java.util.List;

public interface IStudentService {
    public boolean save(Student student);
    public boolean delById(Integer sid);
    public boolean update(Student student);
    public Student findById(Integer sid);
    public List<Student> findPageAll(PageBean pageBean);
    public int rowsAcount();
    public List<Clazz> doinit();
}
