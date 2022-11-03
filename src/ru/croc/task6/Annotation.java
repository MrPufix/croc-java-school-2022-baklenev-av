package ru.croc.task6;

public class Annotation {

    private final Figure figure;
    private final String annotationText;

    public Annotation(Figure figure, String annotationText) {
        this.figure = figure;
        this.annotationText = annotationText;
    }

    public Figure getFigure() {
        return figure;
    }
    public String getText() {
        return annotationText;
    }

    @Override
    public String toString() {
        return figure + ": " + annotationText;
    }
}
