private MethodSpec createBindingConstructorForActivity(){
  MethodSpec.Builder builder=MethodSpec.constructorBuilder().addAnnotation(UI_THREAD).addModifiers(PUBLIC).addParameter(targetTypeName,"target");
  if (constructorNeedsView()) {
    builder.addStatement("this(target, target.getWindow().getDecorView())");
  }
 else {
    builder.addStatement("this(target, target)");
  }
  return builder.build();
}
