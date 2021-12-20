package com.udaychandana.dailywork.controller;

import com.udaychandana.dailywork.entity.Department;
import com.udaychandana.dailywork.service.DepartmentService;
import com.udaychandana.dailywork.service.DepartmentServiceImpl;
import org.hibernate.persister.entity.Loadable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @PostMapping("/departments")
    public Department saveDepartment( @RequestBody Department department ){
        return departmentService.saveDepartment(department);
    }

    @GetMapping("/departments")
    public List<Department> fetchAllDepartment(){
        return departmentService.fetchAllDepartment();
    }
    @GetMapping("/departments/{id}")
    public Department fetchDepartmentById(@PathVariable("id") Long departmentId){
        return departmentService.fetchDepartmentById(departmentId);
    }
    @DeleteMapping("/departments/{id}")
    public void deleteDepartmentById(@PathVariable("id") Long departmentId){
        departmentService.deleteDepartmentById(departmentId);
    }

    @PutMapping("/departments/{id}")
    public Department updateDepartment(@PathVariable ("id") Long departmentId  ,@RequestBody Department department){
        return departmentService.updateDepartment(departmentId, department);
    }

    @GetMapping("/departments/name/{name}")
    public Department fetchDepartmentByName(@PathVariable ("name") String departmentName) {
        return departmentService.fetchDepartmentByName(departmentName);
    }
}
