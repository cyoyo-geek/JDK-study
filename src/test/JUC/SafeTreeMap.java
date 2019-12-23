package test.JUC;

import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class SafeTreeMap {
    private final Map<String, Object> m = new TreeMap<String, Object>();
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private final Lock readLock = lock.readLock();
    private final Lock writeLock = lock.writeLock();

    public Object get(String key) {
        readLock.lock();
        try {
            return m.get(key);
        } finally {
            readLock.unlock();
        }
    }

    public Object put(String key, Object value) {
        writeLock.lock();
        try {
            return m.put(key, value);
        } finally {
            writeLock.unlock();
        }
    }
}
