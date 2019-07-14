/** 
 * Registers new session destroy callback if not already registered.
 */
protected Map<String,BeanData> registerSessionBeans(final HttpSession httpSession){
  SessionBeans sessionBeans=new SessionBeans();
  httpSession.setAttribute(SESSION_BEANS_NAME,sessionBeans);
  return sessionBeans.getBeanMap();
}
