@Override public final void addWithoutGetter(@NonNull TypeSpec.Builder builder,ClassName builderName,CodeBlock.Builder constructorAlways,CodeBlock.Builder constructorWhenAnnotationPresent,CodeBlock.Builder constructorWhenAnnotationMissing){
  TransformedField.Transformable.super.addToBuilder(builder,builderName,constructorAlways,constructorWhenAnnotationPresent,constructorWhenAnnotationMissing);
  addSetter(builder,builderName);
  addInitializer(constructorWhenAnnotationPresent,constructorWhenAnnotationMissing);
}
