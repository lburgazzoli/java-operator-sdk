package io.javaoperatorsdk.operator.processing.event.internal;

import io.fabric8.kubernetes.client.CustomResource;
import java.util.function.BiPredicate;

@FunctionalInterface
public interface CustomResourcePredicate<T extends CustomResource> extends BiPredicate<T, T> {}
