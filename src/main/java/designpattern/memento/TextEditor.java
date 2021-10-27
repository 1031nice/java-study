package designpattern.memento;

public class TextEditor {
    String text = "";

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Memento saveToMemento() {
        System.out.println("save to memento");
        return new Memento(text);
    }

    public void restoreFromMemento(Memento memento) {
        this.text = memento.getText();
        System.out.println("restore from memento");
    }

    static class Memento {
        private final String text;

        public Memento(String text) {
            this.text = text;
        }

        public String getText() {
            return text;
        }
    }
}
