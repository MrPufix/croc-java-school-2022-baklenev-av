package ru.croc.task6;

import javax.security.auth.login.AccountNotFoundException;
import java.util.Random;

public class Test {
    public static void main(String[] args) {
        Annotation rect1 = new Annotation(new Rectangle(100, 20, 32 ,21), "Rect1");
        Annotation rect2 = new Annotation(new Rectangle(1, 2, 5 ,6), "Rectangle label 2");

        Annotation circle1 = new Annotation(new Circle(12, 23, 10), "Circ1");
        Annotation circle2 = new Annotation(new Circle(100, 20, 2), "Some text");

        AnnotatedImage im = new AnnotatedImage("C:\\Documents", rect1, rect2, circle1, circle2);

        System.out.println("Find by point:");
        System.out.println(im.findByPoint(100, 20));
        System.out.println(im.findByPoint(1, 2));
        System.out.println(im.findByPoint(5, 6));
        System.out.println(im.findByPoint(0, 0));

        System.out.println("\nFind by label:");
        System.out.println(im.findByLabel("Rect1"));
        System.out.println(im.findByLabel("label"));
        System.out.println(im.findByLabel("text"));
        System.out.println(im.findByLabel("Word"));

        System.out.println("\nСмещение фигур:");
        System.out.println("Старые значения:");
        Random rand = new Random();
        Annotation[] annotations = im.getAnnotations();
        for(Annotation ann : annotations) {
            System.out.print(ann.toString());
            int dx = rand.nextInt(15);
            int dy = rand.nextInt(15);
            ann.getFigure().move(dx, dy);
            System.out.println(" shifted by " + dx + ", " + dy);
        }
        System.out.println("Новые значения:");
        for(Annotation ann : annotations) {
            System.out.println(ann.toString());
        }

    }
}
