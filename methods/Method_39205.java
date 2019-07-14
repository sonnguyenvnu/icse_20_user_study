/** 
 * Registers Madvoc component <i>instance</i>. Use with caution, as injection of components registered after this will fail.
 */
public WebApp registerComponent(final Object madvocComponent){
  Objects.requireNonNull(madvocComponent);
  madvocComponentInstances.add(madvocComponent);
  return this;
}
