/** 
 * Adds the reference strength methods for the key or value. 
 */
private void addStrength(String collectName,String queueName,TypeName type){
  context.cache.addMethod(MethodSpec.methodBuilder(queueName).addModifiers(context.protectedFinalModifiers()).returns(type).addStatement("return $N",queueName).build());
  context.cache.addField(FieldSpec.builder(type,queueName,Modifier.FINAL).initializer("new $T()",type).build());
  context.cache.addMethod(MethodSpec.methodBuilder(collectName).addModifiers(context.protectedFinalModifiers()).addStatement("return true").returns(boolean.class).build());
}
