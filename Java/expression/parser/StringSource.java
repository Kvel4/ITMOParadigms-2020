package expression.parser;

class StringSource {
    private final String data;
    private int pos;

    StringSource(final String data) {
        this.data = data;
    }

    String getData() {
        return data;
    }

    boolean hasNext() {
        return pos < data.length();
    }

    char next() {
        return data.charAt(pos++);
    }
}
