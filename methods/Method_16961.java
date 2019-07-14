private MethodSpec newLookupKeyMethod(){
  return MethodSpec.methodBuilder("newLookupKey").addJavadoc("Returns a key suitable for looking up an entry in the cache. If the cache " + "holds keys strongly\nthen the key is returned. If the cache holds keys weakly " + "then a {@link $T}\nholding the key argument is returned.\n",lookupKeyType).addModifiers(Modifier.PUBLIC,Modifier.DEFAULT).addParameter(Object.class,"key").addStatement("return key").returns(Object.class).build();
}
