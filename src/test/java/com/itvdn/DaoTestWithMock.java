package com.itvdn;

import com.itvdn.entity.Employee;
import com.itvdn.jpa.EmployeeRepository;
import com.itvdn.service.impl.EmployeeServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class DaoTestWithMock {

    @Mock
    EmployeeRepository employeeRepository;

    @InjectMocks
    EmployeeServiceImpl employeeService;

    private static List<Employee> employeeList = new ArrayList<>();

    static {
        employeeList.add(new Employee("John", "manager", "227"));
        employeeList.add(new Employee("Loise", "assistant", "222"));
    }

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test1() {
        when(employeeRepository.findAll()).thenReturn(employeeList);
        assertEquals(employeeList, employeeService.findAll());
    }

    @Test
    public void test2() {
//        when(employeeRepository.findEmployeeByName("John")).thenReturn(employeeList);
//        assertEquals(employeeList, employeeService.findEmployeeByName("John"));

        when(employeeRepository.findEmployeeByName("John"))
                .thenReturn(employeeList.stream()
                        .filter(e -> e.getName().equals("John"))
                        .collect(Collectors.toList()));
        System.out.println(employeeService.findEmployeeByName("John"));
    }

    @Test
    public void test3() {
        when(employeeRepository.findEmployeeByNameAndPosition("Loise", "assistant")).thenReturn(employeeList);
        assertEquals(employeeList, employeeService.findEmployeeByNameAndPosition("Loise", "assistant"));

    }
}
