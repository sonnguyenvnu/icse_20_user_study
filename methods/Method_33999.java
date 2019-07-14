public void removeToken(String resourceId){
  getSession().removeAttribute(KEY_PREFIX + "#" + resourceId);
}
