private static <T extends Annotation>boolean getEquals(T other,T annotation,Class<T> annotationClass){
  boolean equals=true;
  for (  Method method : annotationClass.getDeclaredMethods()) {
    Class<?> returnType=method.getReturnType();
    Object thisReturnedObject;
    try {
      thisReturnedObject=method.invoke(annotation);
    }
 catch (    IllegalAccessException|InvocationTargetException e) {
      throw new RuntimeException(String.format("Failed to invoke method %s on annotation %s",method,annotation),e);
    }
    Object otherReturnedObject;
    try {
      otherReturnedObject=method.invoke(other);
    }
 catch (    IllegalAccessException|InvocationTargetException e) {
      throw new RuntimeException(String.format("Failed to invoke method %s on annotation %s",method,other),e);
    }
    if (returnType.isArray()) {
      equals&=thisReturnedObject.equals(otherReturnedObject);
    }
 else {
      equals&=thisReturnedObject.equals(otherReturnedObject);
    }
  }
  return equals;
}
