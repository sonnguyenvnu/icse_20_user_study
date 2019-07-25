/** 
 * Create a Next that doesn't update the model but dispatches the supplied effects. 
 */
@Nonnull public static <M,F>Next<M,F> dispatch(Set<? extends F> effects){
  return new AutoValue_Next<>(null,ImmutableUtil.immutableSet(effects));
}
