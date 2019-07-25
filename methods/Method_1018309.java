/** 
 * clone
 * @param method
 * @return
 */
public static Method clone(Method method){
  Method dest=new Method(method.getName());
  for (  String javaDocLine : method.getJavaDocLines()) {
    dest.addJavaDocLine(javaDocLine);
  }
  dest.setReturnType(method.getReturnType());
  for (  Parameter parameter : method.getParameters()) {
    dest.addParameter(JavaElementTools.clone(parameter));
  }
  for (  FullyQualifiedJavaType exception : method.getExceptions()) {
    dest.addException(exception);
  }
  for (  TypeParameter typeParameter : method.getTypeParameters()) {
    dest.addTypeParameter(typeParameter);
  }
  dest.addBodyLines(method.getBodyLines());
  dest.setConstructor(method.isConstructor());
  dest.setNative(method.isNative());
  dest.setSynchronized(method.isSynchronized());
  dest.setDefault(method.isDefault());
  dest.setFinal(method.isFinal());
  dest.setStatic(method.isStatic());
  dest.setVisibility(method.getVisibility());
  return dest;
}
