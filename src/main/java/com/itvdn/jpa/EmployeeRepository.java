package com.itvdn.jpa;

import com.itvdn.entity.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    Employee findById(long id);

    List<Employee> findEmployeeByName(String name);

    List<Employee> findEmployeeByNameAndPosition(String name, String position);

    // пример использования аннотации @Query
    @Query(value = "SELECT e FROM Employee e WHERE e.name=?1 AND e.phone=?2")
    List<Employee> findEmployeeByNameAndPhone(String name, String phone);

    // или по имени, но тогда в параметрах имена нужно замапить
//    @Query("select e from Employee e where e.name=:name and e.phone=:phone")
//    List<Employee> findEmployeeByNameAndPhone(@Param("name") String name, @Param("phone") String phone);
}
