/** 
 * Configures the special kotlin-specific extension. 
 */
public void kotlin(Action<KotlinExtension> closure){
  requireNonNull(closure);
  configure(KotlinExtension.NAME,KotlinExtension.class,closure);
}
