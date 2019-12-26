package test.ArtConcurrentBook.chapter03;

class VolatileFeaturesExample {
    volatile long vl = 0L;

    public void set(long l) {
        vl = l;
    }

    public void getAndIncrement() {
        vl++;
    }

    public long get() {
        return vl;
    }
}
