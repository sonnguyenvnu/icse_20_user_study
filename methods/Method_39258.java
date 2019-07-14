/** 
 * Creates new  {@link Session} with or without custom {@link Properties}.
 * @param properties Custom properties to be used during {@link Session} creation. It is acceptable is this value is {@code null}.
 * @return {@link Session} with any custom {@link Properties}
 */
protected Session createSession(Properties properties){
  if (properties == null) {
    properties=System.getProperties();
  }
  this.session=Session.getInstance(properties);
  return session;
}
