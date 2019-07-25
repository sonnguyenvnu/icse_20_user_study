/** 
 * Configures the special groovy-specific extension. 
 */
public void groovy(Action<GroovyExtension> closure){
  configure(GroovyExtension.NAME,GroovyExtension.class,closure);
}
