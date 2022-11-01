package ru.croc.task5;

public class Test {
    public static void main(String[] args) {
        Annotation rect1 = new Annotation(new Rectangle(100, 20, 32 ,21), "Rect1");
        Annotation circ1 = new Annotation(new Circle(2, 3, 50), "Some text");

        Annotation circ2 = new Annotation(new Circle(55, 1, 2), "Circle 2");
        Annotation rect2 = new Annotation(new Rectangle(3, 4, 55 ,66), "Rectangle 2");

        AnnotatedImage im = new AnnotatedImage("C:\\Documents", rect1, circ1, circ2, rect2);

        System.out.println("Annotations for image at (" + im.getImagePath() + "):");
        Annotation[] annotations = im.getAnnotations();
        for (Annotation ann : annotations) {
            System.out.println(ann.toString());
        }
    }
}
