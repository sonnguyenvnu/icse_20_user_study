private void addField(Class<?> type,String name){
  context.cache.addField(FieldSpec.builder(type,name).build());
  context.cache.addMethod(MethodSpec.methodBuilder(name).addModifiers(context.protectedFinalModifiers()).addStatement("return $L",name).returns(type).build());
  context.cache.addMethod(MethodSpec.methodBuilder("set" + capitalize(name)).addModifiers(context.protectedFinalModifiers()).addParameter(type,name).addStatement("this.$1L = $1L",name).build());
}
