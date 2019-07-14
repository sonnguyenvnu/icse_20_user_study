/** 
 * Returns the  {@link IndexFeatures} of all configured index backends
 */
public Map<String,IndexFeatures> getIndexFeatures(){
  return Maps.transformValues(indexes,new Function<IndexProvider,IndexFeatures>(){
    @Nullable @Override public IndexFeatures apply(    @Nullable IndexProvider indexProvider){
      return indexProvider.getFeatures();
    }
  }
);
}
