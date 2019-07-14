private HandlebarsOptimizedTemplate getTemplate(final TemplateCacheKey key,final String content){
  if (maxCacheEntries != null && maxCacheEntries < 1) {
    return new HandlebarsOptimizedTemplate(handlebars,content);
  }
  try {
    return cache.get(key,new Callable<HandlebarsOptimizedTemplate>(){
      @Override public HandlebarsOptimizedTemplate call(){
        return new HandlebarsOptimizedTemplate(handlebars,content);
      }
    }
);
  }
 catch (  ExecutionException e) {
    return Exceptions.throwUnchecked(e,HandlebarsOptimizedTemplate.class);
  }
}
