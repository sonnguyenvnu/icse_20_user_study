@Override public void addToBuilderInterface(@NonNull TypeSpec.Builder builder,@NonNull ClassName builderName){
  if (modifiers.contains(Modifier.PUBLIC)) {
    builder.addMethod(baseMethod(builderName).addModifiers(Modifier.ABSTRACT).build());
  }
}
