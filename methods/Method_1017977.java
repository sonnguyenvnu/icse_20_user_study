@Nonnull @Override public final R options(@Nullable final scala.collection.Map<String,String> options){
  if (options != null) {
    options(JavaConversions.mapAsJavaMap(options));
  }
  return (R)this;
}
