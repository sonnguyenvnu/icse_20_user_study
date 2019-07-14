static Fix replaceTargetAnnotation(AnnotationTree targetAnnotationTree,Set<ElementType> types){
  Builder builder=SuggestedFix.builder().replace(targetAnnotationTree,"@Target({" + Joiner.on(", ").join(types) + "})");
  for (  ElementType type : types) {
    builder.addStaticImport("java.lang.annotation.ElementType." + type);
  }
  return builder.build();
}
