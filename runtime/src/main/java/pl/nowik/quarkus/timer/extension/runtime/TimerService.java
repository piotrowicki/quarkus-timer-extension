package pl.nowik.quarkus.timer.extension.runtime;

import jakarta.enterprise.context.ApplicationScoped;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.LongAdder;

@ApplicationScoped
public class TimerService {

    public static class Stats {
        public final LongAdder count = new LongAdder();
        public final LongAdder totalNs = new LongAdder();
    }

    private final ConcurrentMap<String, Stats> map = new ConcurrentHashMap<>();

    public void record(String name, long nanos) {
        Stats s = map.computeIfAbsent(name, k -> new Stats());
        s.count.increment();
        s.totalNs.add(nanos);
    }

    public Stats getStats(String name) {
        return map.get(name);
    }
}
