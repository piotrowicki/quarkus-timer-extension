package pl.nowik.quarkus.timer.extension.runtime;

import java.util.logging.Logger;

import jakarta.annotation.Priority;
import jakarta.inject.Inject;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;

@Timed
@Interceptor
@Priority(Interceptor.Priority.APPLICATION + 10)
public class TimedInterceptor {

    private static final Logger LOG = Logger.getLogger(TimedInterceptor.class.getName());

    @Inject
    TimerService timerService;

    @AroundInvoke
    public Object aroundInvoke(InvocationContext ctx) throws Exception {
        long start = System.nanoTime();
        try {
            return ctx.proceed();
        } finally {
            long durNs = System.nanoTime() - start;
            String name = ctx.getMethod().getDeclaringClass().getName() + "#" + ctx.getMethod().getName();
            timerService.record(name, durNs);
            LOG.info("\nTimer for: " + name + " is totalNs: "+ timerService.getStats(name).totalNs);
        }
    }
}
