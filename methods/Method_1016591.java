/** 
 * Configures the special java-specific extension. 
 */
public void java(Action<JavaExtension> closure){
  requireNonNull(closure);
  configure(JavaExtension.NAME,JavaExtension.class,closure);
}
