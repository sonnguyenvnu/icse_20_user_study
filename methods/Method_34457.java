private Annotation cacheKeyAnnotation(){
  return Iterables.tryFind(annotations,new Predicate<Annotation>(){
    @Override public boolean apply(    Annotation input){
      return input.annotationType().equals(CacheKey.class);
    }
  }
).orNull();
}
