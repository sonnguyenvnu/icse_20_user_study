public static String[] lookupParameterNames(AccessibleObject methodOrCtor){
  if (IS_ANDROID) {
    return new String[0];
  }
  final Class<?>[] types;
  final Class<?> declaringClass;
  final String name;
  Annotation[][] parameterAnnotations;
  if (methodOrCtor instanceof Method) {
    Method method=(Method)methodOrCtor;
    types=method.getParameterTypes();
    name=method.getName();
    declaringClass=method.getDeclaringClass();
    parameterAnnotations=method.getParameterAnnotations();
  }
 else {
    Constructor<?> constructor=(Constructor<?>)methodOrCtor;
    types=constructor.getParameterTypes();
    declaringClass=constructor.getDeclaringClass();
    name="<init>";
    parameterAnnotations=constructor.getParameterAnnotations();
  }
  if (types.length == 0) {
    return new String[0];
  }
  ClassLoader classLoader=declaringClass.getClassLoader();
  if (classLoader == null) {
    classLoader=ClassLoader.getSystemClassLoader();
  }
  String className=declaringClass.getName();
  String resourceName=className.replace('.','/') + ".class";
  InputStream is=classLoader.getResourceAsStream(resourceName);
  if (is == null) {
    return new String[0];
  }
  try {
    ClassReader reader=new ClassReader(is,false);
    TypeCollector visitor=new TypeCollector(name,types);
    reader.accept(visitor);
    String[] parameterNames=visitor.getParameterNamesForMethod();
    for (int i=0; i < parameterNames.length; i++) {
      Annotation[] annotations=parameterAnnotations[i];
      if (annotations != null) {
        for (int j=0; j < annotations.length; j++) {
          if (annotations[j] instanceof JSONField) {
            JSONField jsonField=(JSONField)annotations[j];
            String fieldName=jsonField.name();
            if (fieldName != null && fieldName.length() > 0) {
              parameterNames[i]=fieldName;
            }
          }
        }
      }
    }
    return parameterNames;
  }
 catch (  IOException e) {
    return new String[0];
  }
 finally {
    IOUtils.close(is);
  }
}
