public void clear(){
synchronized (session) {
    Enumeration<String> ems=session.getAttributeNames();
    List<String> keys=new ArrayList<String>();
    while (ems.hasMoreElements()) {
      String key=ems.nextElement();
      if (null == key)       continue;
      Object value=session.getAttribute(key);
      if (value instanceof ObjectProxy) {
        keys.add(key);
        ((ObjectProxy)value).depose();
      }
    }
    for (    String key : keys) {
      session.removeAttribute(key);
    }
  }
}
