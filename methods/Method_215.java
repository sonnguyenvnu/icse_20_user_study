private MethodSpec createBindingViewDelegateConstructor(){
  return MethodSpec.constructorBuilder().addJavadoc("@deprecated Use {@link #$T($T, $T)} for direct creation.\n    " + "Only present for runtime invocation through {@code ButterKnife.bind()}.\n",bindingClassName,targetTypeName,CONTEXT).addAnnotation(Deprecated.class).addAnnotation(UI_THREAD).addModifiers(PUBLIC).addParameter(targetTypeName,"target").addParameter(VIEW,"source").addStatement(("this(target, source.getContext())")).build();
}
