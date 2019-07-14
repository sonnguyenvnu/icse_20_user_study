/** 
 * Scan Field
 * @param xob XAnnotated Object
 */
private void scan(XAnnotatedObject xob){
  Field[] fields=xob.klass.getDeclaredFields();
  for (  Field field : fields) {
    Annotation anno=checkMemberAnnotation(field);
    if (anno != null) {
      XAnnotatedMember member=createFieldMember(field,anno);
      if (member == null) {
        member=createExtendFieldMember(field,anno,xob);
      }
      xob.addMember(member);
    }
  }
  Method[] methods=xob.klass.getDeclaredMethods();
  for (  Method method : methods) {
    Class[] paramTypes=method.getParameterTypes();
    if (paramTypes.length != 1) {
      continue;
    }
    Annotation anno=checkMemberAnnotation(method);
    if (anno != null) {
      XAnnotatedMember member=createMethodMember(method,xob.klass,anno);
      if (member == null) {
        member=createExtendMethodMember(method,anno,xob);
      }
      xob.addMember(member);
    }
  }
}
