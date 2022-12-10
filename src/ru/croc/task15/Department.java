package ru.croc.task15;

import java.util.ArrayList;
import java.util.List;

public class Department {
    private final List<Department> children;
    private final int handleTime;
    private final String name;

    public Department(String name, int handleTime) {
        this.name = name;
        this.handleTime = handleTime;
        this.children = new ArrayList<>();
    }

    public void addChild(Department child) {
        children.add(child);
    }

    public int countRequestHandleTime() {
        int max = 0;
        for(Department department : children) {
            int res = department.countRequestHandleTime();
            if(res > max)
                max = res;
        }
        return handleTime + max;
    }

    public void printHierarchy(int level) {
        System.out.println(" ".repeat(level) + name);
        for(Department child : children) {
            child.printHierarchy(level + 1);
        }
    }
}
