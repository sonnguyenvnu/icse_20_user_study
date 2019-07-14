@Override protected void execute(){
  context.cache.superclass(context.superClass).addAnnotation(AnnotationSpec.builder(SuppressWarnings.class).addMember("value","{$S, $S, $S}","unchecked","MissingOverride","NullAway").build()).addJavadoc(getJavaDoc()).addTypeVariable(kTypeVar).addTypeVariable(vTypeVar);
  if (context.isFinal) {
    context.cache.addModifiers(Modifier.FINAL);
  }
}
