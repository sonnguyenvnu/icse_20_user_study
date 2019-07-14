private MethodSpec createBindingConstructor(int sdk,boolean debuggable){
  MethodSpec.Builder constructor=MethodSpec.constructorBuilder().addAnnotation(UI_THREAD).addModifiers(PUBLIC);
  if (hasMethodBindings()) {
    constructor.addParameter(targetTypeName,"target",FINAL);
  }
 else {
    constructor.addParameter(targetTypeName,"target");
  }
  if (constructorNeedsView()) {
    constructor.addParameter(VIEW,"source");
  }
 else {
    constructor.addParameter(CONTEXT,"context");
  }
  if (hasUnqualifiedResourceBindings()) {
    constructor.addAnnotation(AnnotationSpec.builder(SuppressWarnings.class).addMember("value","$S","ResourceType").build());
  }
  if (hasOnTouchMethodBindings()) {
    constructor.addAnnotation(AnnotationSpec.builder(SUPPRESS_LINT).addMember("value","$S","ClickableViewAccessibility").build());
  }
  if (parentBinding != null) {
    if (parentBinding.constructorNeedsView()) {
      constructor.addStatement("super(target, source)");
    }
 else     if (constructorNeedsView()) {
      constructor.addStatement("super(target, source.getContext())");
    }
 else {
      constructor.addStatement("super(target, context)");
    }
    constructor.addCode("\n");
  }
  if (hasTargetField()) {
    constructor.addStatement("this.target = target");
    constructor.addCode("\n");
  }
  if (hasViewBindings()) {
    if (hasViewLocal()) {
      constructor.addStatement("$T view",VIEW);
    }
    for (    ViewBinding binding : viewBindings) {
      addViewBinding(constructor,binding,debuggable);
    }
    for (    FieldCollectionViewBinding binding : collectionBindings) {
      constructor.addStatement("$L",binding.render(debuggable));
    }
    if (!resourceBindings.isEmpty()) {
      constructor.addCode("\n");
    }
  }
  if (!resourceBindings.isEmpty()) {
    if (constructorNeedsView()) {
      constructor.addStatement("$T context = source.getContext()",CONTEXT);
    }
    if (hasResourceBindingsNeedingResource(sdk)) {
      constructor.addStatement("$T res = context.getResources()",RESOURCES);
    }
    for (    ResourceBinding binding : resourceBindings) {
      constructor.addStatement("$L",binding.render(sdk));
    }
  }
  return constructor.build();
}
