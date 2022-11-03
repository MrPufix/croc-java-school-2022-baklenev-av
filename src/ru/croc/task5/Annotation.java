package ru.croc.task5;

public class Annotation {

    private final Figure figure;
    private final String annotationText;

    public Annotation(Figure figure, String annotationText) {
        this.figure = figure;
        this.annotationText = annotationText;
    }

    @Override
    public String toString() {
        return figure + ": " + annotationText;
    }
}
