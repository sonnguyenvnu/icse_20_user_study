/** 
 * Creates a  {@link AndroidLifecycleScopeProvider} for Android Lifecycles.
 * @param lifecycle the lifecycle to scope for.
 * @return a {@link AndroidLifecycleScopeProvider} against this lifecycle.
 */
public static AndroidLifecycleScopeProvider from(Lifecycle lifecycle){
  return from(lifecycle,DEFAULT_CORRESPONDING_EVENTS);
}
