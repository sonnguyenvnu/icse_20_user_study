default void addToConfig(@NonNull TypeSpec.Builder builder,@NonNull MethodSpec.Builder constructor){
  final TypeName type=Types.getImmutableType(getType());
  builder.addField(FieldSpec.builder(type,getName(),Modifier.PRIVATE,Modifier.FINAL).addAnnotations(getAnnotations()).build());
  if (!type.equals(getType())) {
    constructor.addStatement("$1L = new $2T($3L.$1L())",getName(),type,PARAM_0);
  }
 else {
    constructor.addStatement("$1L = $2L.$1L()",getName(),PARAM_0);
  }
  builder.addMethod(MethodSpec.methodBuilder(getName()).addAnnotations(getAnnotations()).returns(type).addStatement("return $L",getName()).addModifiers(Modifier.PUBLIC).build());
}
