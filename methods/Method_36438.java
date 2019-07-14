/** 
 * Registers a mappable object class. The class will be scanned for XMap annotations and a mapping description is created.
 * @param klass the object class
 * @return the mapping description
 */
@SuppressWarnings("unchecked") public XAnnotatedObject register(Class klass,boolean isParent){
  XAnnotatedObject xao=objects.get(klass);
  if (xao == null) {
    XObject xob=checkObjectAnnotation(klass,klass.getClassLoader());
    if (xob != null) {
      xao=new XAnnotatedObject(this,klass,xob);
      objects.put(xao.klass,xao);
      if (isParent) {
        scanParent(xao);
      }
 else {
        scan(xao);
      }
      String key=xob.value();
      if (key.length() > 0) {
        roots.put(xao.path.path,xao);
      }
    }
  }
  return xao;
}
