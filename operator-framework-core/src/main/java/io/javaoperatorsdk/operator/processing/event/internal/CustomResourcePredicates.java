package io.javaoperatorsdk.operator.processing.event.internal;

import io.fabric8.kubernetes.client.CustomResource;

public final class CustomResourcePredicates {

  private static final CustomResourcePredicate<CustomResource> GENERATION_AWARE =
      new CustomResourcePredicate<>() {
        @Override
        public boolean test(CustomResource oldResource, CustomResource newResource) {
          if (oldResource == null) {
            return true;
          }
          if (newResource.isMarkedForDeletion()) {
            return true;
          }
          return oldResource.getMetadata().getGeneration()
              < newResource.getMetadata().getGeneration();
        }
      };

  private static final CustomResourcePredicate<CustomResource> PASSTHROUGH =
      new CustomResourcePredicate<>() {
        @Override
        public boolean test(CustomResource oldResource, CustomResource newResource) {
          return true;
        }
      };

  private CustomResourcePredicates() {}

  @SuppressWarnings("unchecked")
  public static <T extends CustomResource> CustomResourcePredicate<T> passthrough() {
    return (CustomResourcePredicate<T>) PASSTHROUGH;
  }

  @SuppressWarnings("unchecked")
  public static <T extends CustomResource> CustomResourcePredicate<T> generationAware() {
    return (CustomResourcePredicate<T>) GENERATION_AWARE;
  }
}
