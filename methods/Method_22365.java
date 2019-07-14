@Override public final void addToBuilder(@NonNull TypeSpec.Builder builder,@NonNull ClassName builderName,@NonNull CodeBlock.Builder constructorAlways,@NonNull CodeBlock.Builder constructorWhenAnnotationPresent,CodeBlock.Builder constructorWhenAnnotationMissing){
  addWithoutGetter(builder,builderName,constructorAlways,constructorWhenAnnotationPresent,constructorWhenAnnotationMissing);
  addGetter(builder);
}
