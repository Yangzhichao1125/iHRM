package com.ihrm.company.service;

import com.ihrm.common.service.BaseService;
import com.ihrm.common.utils.IdWorker;
import com.ihrm.company.dao.DepartmentDao;
import com.ihrm.domain.company.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.swing.text.html.HTMLDocument;
import java.util.List;

/**
 * @author: hyl
 * @date: 2020/01/03
 **/

@Service
public class DepartmentService extends BaseService {

    @Autowired
    private DepartmentDao departmentDao;

    @Autowired
    private IdWorker idWorker;

    /**
     * 保存部门
     */
    public void save(Department department){
        //设置主键
        String id = idWorker.nextId() + "";
        department.setId(id);
        //调用dao保存部门
        departmentDao.save(department);
    }

    /**
     * 更新部门
     */
    public void update(Department department){
        //根据id查询部门
        Department dept = departmentDao.findById(department.getId()).get();
        //设置部门属性
        dept.setCode(department.getCode());
        dept.setIntroduce(department.getIntroduce());
        dept.setName(department.getName());
        //更新部门
        departmentDao.save(dept);
    }


    /**
     * 根据id查询部门
     */
    public Department findById(String id){
        return departmentDao.findById(id).get();
    }


    /**
     * 查询全部部门列表
     */
    public List<Department> findAll(String companyId){


        return departmentDao.findAll(getSpec(companyId));
    }

    /**
     * 根据id删除部门
     */
    public void deleteById(String id){
        departmentDao.deleteById(id);
    }
}
