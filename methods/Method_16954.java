/** 
 * Creates an accessor that returns the unwrapped variable. 
 */
protected final MethodSpec newGetter(Strength strength,TypeName varType,String varName,Visibility visibility){
  MethodSpec.Builder getter=MethodSpec.methodBuilder("get" + capitalize(varName)).addModifiers(context.publicFinalModifiers()).returns(varType);
  String type;
  if (varType.isPrimitive()) {
    type=varType.equals(TypeName.INT) ? "Int" : "Long";
  }
 else {
    type="Object";
  }
  if (strength == Strength.STRONG) {
    if (visibility.isRelaxed) {
      if (varType.isPrimitive()) {
        getter.addStatement("return $T.UNSAFE.get$N(this, $N)",UNSAFE_ACCESS,type,offsetName(varName));
      }
 else {
        getter.addStatement("return ($T) $T.UNSAFE.get$N(this, $N)",varType,UNSAFE_ACCESS,type,offsetName(varName));
      }
    }
 else {
      getter.addStatement("return $N",varName);
    }
  }
 else {
    if (visibility.isRelaxed) {
      getter.addStatement("return (($T<$T>) $T.UNSAFE.get$N(this, $N)).get()",Reference.class,varType,UNSAFE_ACCESS,type,offsetName(varName));
    }
 else {
      getter.addStatement("return $N.get()",varName);
    }
  }
  return getter.build();
}
