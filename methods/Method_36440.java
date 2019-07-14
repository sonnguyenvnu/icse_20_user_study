private void scanParent(XAnnotatedObject xob){
  Class scanClass=xob.klass;
  for (; scanClass != Object.class; scanClass=scanClass.getSuperclass()) {
    for (    Method method : scanClass.getDeclaredMethods()) {
      Class[] paramTypes=method.getParameterTypes();
      if (paramTypes.length != 1) {
        continue;
      }
      Annotation anno=checkMemberAnnotation(method);
      if (anno != null) {
        XAnnotatedMember member=createMethodMember(method,xob.klass,anno);
        xob.addMember(member);
      }
    }
    for (    Field field : scanClass.getDeclaredFields()) {
      Annotation anno=checkMemberAnnotation(field);
      if (anno != null) {
        XAnnotatedMember member=createFieldMember(field,anno);
        xob.addMember(member);
      }
    }
  }
}
