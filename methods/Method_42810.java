/** 
 * ??application
 * @return
 */
protected ServletContext getApplication(){
  return ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest().getSession().getServletContext();
}
