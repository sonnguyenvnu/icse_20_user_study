public static TypeSpecDataHolder generate(SpecModel specModel){
  final TypeSpecDataHolder.Builder holder=TypeSpecDataHolder.newBuilder();
  if (specModel.hasInjectedDependencies()) {
    for (    AnnotationSpec annotation : specModel.getClassAnnotations()) {
      if (specModel.getDependencyInjectionHelper().isValidGeneratedComponentAnnotation(annotation)) {
        holder.addAnnotation(annotation);
      }
    }
  }
 else {
    holder.addAnnotations(specModel.getClassAnnotations());
  }
  return holder.build();
}
