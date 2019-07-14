@Override protected void execute(){
  boolean fastpath=Feature.usesFastPath(Sets.union(context.parentFeatures,context.generateFeatures));
  context.cache.addMethod(MethodSpec.methodBuilder("fastpath").addStatement("return " + Boolean.toString(fastpath)).addModifiers(Modifier.PROTECTED).returns(boolean.class).build());
}
