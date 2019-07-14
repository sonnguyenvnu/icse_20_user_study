/** 
 * Creates the setValue method. 
 */
private MethodSpec makeSetValue(){
  MethodSpec.Builder setter=MethodSpec.methodBuilder("setValue").addModifiers(context.publicFinalModifiers()).addParameter(vTypeVar,"value").addParameter(vRefQueueType,"referenceQueue");
  if (isStrongValues()) {
    setter.addStatement("$T.UNSAFE.putObject(this, $N, $N)",UNSAFE_ACCESS,offsetName("value"),"value");
  }
 else {
    setter.addStatement("(($T<V>) getValueReference()).clear()",Reference.class);
    setter.addStatement("$T.UNSAFE.putObject(this, $N, new $T($L, $N, referenceQueue))",UNSAFE_ACCESS,offsetName("value"),valueReferenceType(),"getKeyReference()","value");
  }
  return setter.build();
}
