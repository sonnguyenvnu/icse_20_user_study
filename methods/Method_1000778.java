public boolean save(String scope,String name,ObjectProxy obj){
  if (null != scope && "request".equals(scope)) {
    req.setAttribute(name,obj);
    return true;
  }
  return false;
}
