public static Iterable<Object> getValues(JanusGraphElement element,PropertyKey key){
  if (element instanceof JanusGraphRelation) {
    Object value=element.valueOrNull(key);
    if (value == null)     return Collections.EMPTY_LIST;
 else     return ImmutableList.of(value);
  }
 else {
    assert element instanceof JanusGraphVertex;
    return Iterables.transform((((JanusGraphVertex)element).query()).keys(key.name()).properties(),new Function<JanusGraphVertexProperty,Object>(){
      @Nullable @Override public Object apply(      final JanusGraphVertexProperty janusgraphProperty){
        return janusgraphProperty.value();
      }
    }
);
  }
}
