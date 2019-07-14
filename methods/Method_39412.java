/** 
 * Returns instance map from http session.
 */
@SuppressWarnings("unchecked") protected Map<String,BeanData> getSessionMap(final HttpSession session){
  SessionBeans sessionBeans=(SessionBeans)session.getAttribute(SESSION_BEANS_NAME);
  if (sessionBeans == null) {
    return null;
  }
  return sessionBeans.getBeanMap();
}
