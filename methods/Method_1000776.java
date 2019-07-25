public void clear(){
synchronized (req) {
    Enumeration<String> ems=req.getAttributeNames();
    List<String> keys=new ArrayList<String>();
    while (ems.hasMoreElements()) {
      String key=ems.nextElement();
      if (null == key)       continue;
      Object value=req.getAttribute(key);
      if (value instanceof ObjectProxy) {
        keys.add(key);
        ((ObjectProxy)value).depose();
      }
    }
    for (    String key : keys) {
      req.removeAttribute(key);
    }
  }
}
