private LocalCacheSelectorCode selector(){
  block.beginControlFlow("try").addStatement("$T<?> clazz = $T.class.getClassLoader().loadClass(sb.toString())",Class.class,LOCAL_CACHE_FACTORY).addStatement("$T<?> ctor = clazz.getDeclaredConstructor($T.class, $T.class, $T.class)",Constructor.class,BUILDER,CACHE_LOADER.rawType,TypeName.BOOLEAN).add("@SuppressWarnings($S)\n","unchecked").addStatement("$1T factory = ($1T) ctor.newInstance(builder, cacheLoader, async)",BOUNDED_LOCAL_CACHE).addStatement("return factory").nextControlFlow("catch ($T e)",ReflectiveOperationException.class).addStatement("throw new $T(sb.toString(), e)",IllegalStateException.class).endControlFlow();
  return this;
}
