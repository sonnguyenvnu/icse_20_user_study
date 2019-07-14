@Override protected void execute(){
  String cacheLoader=context.superClass.equals(BOUNDED_LOCAL_CACHE) ? "(CacheLoader<K, V>) cacheLoader" : "cacheLoader";
  context.constructor.addParameter(BUILDER_PARAM).addParameter(CACHE_LOADER_PARAM).addParameter(boolean.class,"async").addStatement("super(builder, $L, async)",cacheLoader);
}
