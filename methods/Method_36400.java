@SuppressWarnings("unchecked") public XAnnotatedObject register(Class klass,ApplicationContext applicationContext){
  XAnnotatedObject xao=objects.get(klass);
  if (xao == null) {
    XObject xob=checkObjectAnnotation(klass,klass.getClassLoader());
    if (xob != null) {
      xao=new XAnnotatedSpringObject(this,klass,xob,applicationContext);
      objects.put(xao.klass,xao);
      scan(xao);
      String key=xob.value();
      if (key.length() > 0) {
        roots.put(xao.path.path,xao);
      }
    }
  }
  return xao;
}
