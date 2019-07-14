private void addFieldAndMethod(TypeName type,String name){
  context.cache.addField(FieldSpec.builder(type,name,Modifier.FINAL).build());
  context.cache.addMethod(MethodSpec.methodBuilder(name).addModifiers(context.protectedFinalModifiers()).addStatement("return " + name).returns(type).build());
}
