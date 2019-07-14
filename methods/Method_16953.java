/** 
 * Creates an accessor that returns the reference. 
 */
protected final MethodSpec newGetRef(String varName){
  MethodSpec.Builder getter=MethodSpec.methodBuilder("get" + capitalize(varName) + "Reference").addModifiers(context.publicFinalModifiers()).returns(Object.class);
  getter.addStatement("return $T.UNSAFE.getObject(this, $N)",UNSAFE_ACCESS,offsetName(varName));
  return getter.build();
}
