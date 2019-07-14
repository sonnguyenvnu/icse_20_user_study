/** 
 * Creates a mutator to the variable. 
 */
protected final MethodSpec newSetter(TypeName varType,String varName,Visibility visibility){
  String methodName="set" + Character.toUpperCase(varName.charAt(0)) + varName.substring(1);
  String type;
  if (varType.isPrimitive()) {
    type=varType.equals(TypeName.INT) ? "Int" : "Long";
  }
 else {
    type="Object";
  }
  MethodSpec.Builder setter=MethodSpec.methodBuilder(methodName).addModifiers(context.publicFinalModifiers()).addParameter(varType,varName);
  if (visibility.isRelaxed) {
    setter.addStatement("$T.UNSAFE.put$L(this, $N, $N)",UNSAFE_ACCESS,type,offsetName(varName),varName);
  }
 else {
    setter.addStatement("this.$N = $N",varName,varName);
  }
  return setter.build();
}
