/** 
 * Registers additional Madvoc components after the registration of default components.
 */
public WebApp registerComponent(final Class<?> madvocComponent){
  Objects.requireNonNull(madvocComponent);
  madvocComponents.add(ClassConsumer.of(madvocComponent));
  return this;
}
