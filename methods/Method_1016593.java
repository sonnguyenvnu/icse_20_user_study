/** 
 * Configures the special freshmark-specific extension. 
 */
public void freshmark(Action<FreshMarkExtension> closure){
  requireNonNull(closure);
  configure(FreshMarkExtension.NAME,FreshMarkExtension.class,closure);
}
