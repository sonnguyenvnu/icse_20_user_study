public static Iterable<? extends Element> getQueryResults(CompositeIndexType index,Object[] values,StandardJanusGraphTx tx){
  GraphCentricQueryBuilder gb=getQuery(index,values,tx);
switch (index.getElement()) {
case VERTEX:
    return gb.vertices();
case EDGE:
  return gb.edges();
case PROPERTY:
return gb.properties();
default :
throw new AssertionError();
}
}
