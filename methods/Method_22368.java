default void addToBuilder(@NonNull TypeSpec.Builder builder,@NonNull ClassName builderName,@NonNull CodeBlock.Builder constructorAlways,@NonNull CodeBlock.Builder constructorWhenAnnotationPresent,CodeBlock.Builder constructorWhenAnnotationMissing){
  final FieldSpec.Builder field=FieldSpec.builder(getType(),getName(),Modifier.PRIVATE).addAnnotations(getAnnotations());
  configureField(field);
  builder.addField(field.build());
}
