/** 
 * Alternative way for registering Joy listeners. Sometimes servlet container does not allow adding new listener from already added listener. This method therefore registers the listener <i>before</i> container actually called the callback methods.
 */
public static void registerInServletContext(final ServletContext servletContext,final Class<? extends JoyContextListener> joyContextListenerClass){
  try {
    final JoyContextListener joyContextListener=ClassUtil.newInstance(joyContextListenerClass);
    joyContextListener.createJoyAndInitServletContext(servletContext);
  }
 catch (  Exception e) {
    throw new JoyException(e);
  }
  servletContext.addListener(joyContextListenerClass);
}
