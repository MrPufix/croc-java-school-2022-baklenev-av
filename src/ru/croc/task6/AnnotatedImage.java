package ru.croc.task6;

class AnnotatedImage {

    private final String imagePath;

    private final Annotation[] annotations;

    public AnnotatedImage(String imagePath, Annotation... annotations) {
        this.imagePath = imagePath;
        this.annotations = annotations;
    }

    public String getImagePath() {
        return this.imagePath;
    }

    public Annotation[] getAnnotations() {
        return this.annotations;
    }

    public Annotation findByPoint (int x, int y) {
        for(Annotation annotation : annotations) {
            Figure figure = annotation.getFigure();
            if(figure.hasPoint(x, y))
                return annotation;
        }
        return null;
    }

    public Annotation findByLabel (String label) {
        for(Annotation annotation : annotations) {
            String annotationText = annotation.getText();
            if(annotationText.contains(label)) {
                return annotation;
            }
        }
        return null;
    }
}

