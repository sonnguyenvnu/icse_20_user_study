/** 
 * Returns application context set during the initialization.
 */
public ServletContext getApplicationContext(){
  return servletContextProvider.get();
}
