public boolean save(String scope,String name,ObjectProxy obj){
  if (null != scope && "session".equals(scope)) {
    session.setAttribute(name,obj);
    return true;
  }
  return false;
}
