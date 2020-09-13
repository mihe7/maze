public class Size {
    public final int width;
    public final int height;

    public Size(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || o == this || !(o instanceof Size)) {
            return o == this;
        }

        Size s = (Size) o;
        return width == s.width && height == s.height;
    }

    @Override
    public int hashCode() {
        return 7*width + 53*height;
    }
}

