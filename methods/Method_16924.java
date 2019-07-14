private void addWeakKeys(){
  context.nodeSubtype.addMethod(MethodSpec.methodBuilder("newLookupKey").addModifiers(Modifier.PUBLIC).addParameter(Object.class,"key").addStatement("return new $T<>(key)",lookupKeyType).returns(Object.class).build());
  context.nodeSubtype.addMethod(MethodSpec.methodBuilder("newReferenceKey").addModifiers(Modifier.PUBLIC).addParameter(kTypeVar,"key").addParameter(kRefQueueType,"referenceQueue").addStatement("return new $T($L, $L)",referenceKeyType,"key","referenceQueue").returns(Object.class).build());
}
