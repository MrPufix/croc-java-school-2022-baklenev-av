package ru.croc.task15;

import java.util.ArrayList;
import java.util.List;

public class Department {
    private final Department parent;
    private final List<Department> children;
    private final int handleTime;
    private final String name;

    public Department(Department parent, int handleTime, String name) {
        this.parent = parent;
        this.handleTime = handleTime;
        this.name = name;
        this.children = new ArrayList<>();

        if(parent!=null)
            parent.addChild(this);
    }

    public void addChild(Department child) {
        children.add(child);
    }

    public Department getDepartmentByName(String name) {
        if(this.name.equals(name))
            return this;
        for (Department department : children) {
            Department found = department.getDepartmentByName(name);
            if(found != null)
                return found;
        }
        return null;
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
