/** 
 * Configures the special C/C++-specific extension. 
 */
public void cpp(Action<CppExtension> closure){
  configure(CppExtension.NAME,CppExtension.class,closure);
}
