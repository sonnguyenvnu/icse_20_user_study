public ObjectProxy fetch(String name){
  Object re=session.getAttribute(name);
  if (re == null)   return null;
  if (re instanceof ObjectProxy)   return (ObjectProxy)re;
  return new ObjectProxy().setObj(re);
}
