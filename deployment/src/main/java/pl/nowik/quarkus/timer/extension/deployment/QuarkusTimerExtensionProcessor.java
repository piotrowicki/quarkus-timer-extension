package pl.nowik.quarkus.timer.extension.deployment;

import io.quarkus.arc.deployment.AdditionalBeanBuildItem;
import io.quarkus.deployment.annotations.BuildStep;
import io.quarkus.deployment.builditem.FeatureBuildItem;

class QuarkusTimerExtensionProcessor {

    private static final String FEATURE = "quarkus-timer-extension";

    @BuildStep
    FeatureBuildItem feature() {
        return new FeatureBuildItem(FEATURE);
    }

    @BuildStep
    AdditionalBeanBuildItem registerBeans() {
        return AdditionalBeanBuildItem.builder()
                .addBeanClass("pl.nowik.quarkus.timer.extension.runtime.TimedInterceptor")
                .addBeanClass("pl.nowik.quarkus.timer.extension.runtime.TimerService")
                .setUnremovable()
                .build();
    }
}
