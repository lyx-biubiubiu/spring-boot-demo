package com.lyx.controller;

import com.lyx.dao.DepartmentDao;
import com.lyx.dao.EmployeeDao;
import com.lyx.entities.Department;
import com.lyx.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Collection;

@Controller
public class EmployeeIdController {

    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private DepartmentDao departmentDao;

    /**
     * 员工列表
     * @param model
     * @return
     */
    @GetMapping("emps")
    public String emps(Model model){
        Collection<Employee> all = employeeDao.getAll();
        model.addAttribute("emps",all);

        return "emp/list";
    }

    /**
     * 来到员工添加页面
     * @param model
     * @return
     */
    @GetMapping("emp")
    public String emp(Model model, HttpServletRequest request){
        HttpSession session = request.getSession();
         System.out.println(session.getAttribute("111"));
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts",departments);
        return "emp/add";
    }

    /**
     * 员工添加
     * @param employee
     * @return
     */
    @PostMapping("emp")
    public String addemp(Employee employee){
        Department department = departmentDao.getDepartment(employee.getDepartment().getId());
        employee.setDepartment(department);
        employeeDao.save(employee);
        return "redirect:/emps";
    }


}
