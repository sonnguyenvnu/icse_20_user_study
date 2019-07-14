/** 
 * This method should be called in the  {@link android.app.Application} to ensure that the correctclass loader is set up when we load the Litho spec classes.
 */
public static synchronized void init(ClassLoader classLoader){
  sSpecClassLoader=classLoader;
}
