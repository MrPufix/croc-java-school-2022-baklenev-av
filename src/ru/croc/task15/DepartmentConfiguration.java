package ru.croc.task15;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;

public class DepartmentConfiguration {
    private final Department headDepartment;

    private DepartmentConfiguration(Department headDepartment) {
        this.headDepartment = headDepartment;
    }

    /**
     * Creates department configuration from file.
     * File contains department name, parent department name, request handle time
     * separated by commas on a new line for each department.
     * A department must have a parent department declared before its own declaration
     * @param fileName - name of source file
     * @return new DepartmentConfiguration based on file data
     * @throws FileNotFoundException if file doesn't exist
     */
    public static DepartmentConfiguration createFromFile(String fileName) throws FileNotFoundException {
        Department head = null;
        try(Scanner scanner = new Scanner(new File(fileName))) {
            Map<String, Department> departments = new HashMap<>();
            while (scanner.hasNext()) {
                String[] data = scanner.nextLine().split(",");

                String name = data[0];
                String parentName = data[1];
                int handleTime = Integer.parseInt(data[2]);

                if(parentName.equals("-")) {
                    head = new Department(name, handleTime);
                    departments.put(name, head);
                } else {
                    if(head == null) continue;
                    Department parent = departments.get(parentName);
                    Department department = new Department(name, handleTime);
                    parent.addChild(department);
                    departments.put(name, department);
                }
            }
        }
        return new DepartmentConfiguration(head);
    }

    /**
     * Counts request handle time for this department configuration
     * @return request handle time for configuration
     */
    public int countRequestHandleTime() {
        return headDepartment.countRequestHandleTime();
    }
}
