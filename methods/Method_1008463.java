@Override protected TokenStream normalize(String fieldName,TokenStream in){
  TokenStream result=in;
  for (  TokenFilterFactory filter : tokenFilters) {
    if (filter instanceof MultiTermAwareComponent) {
      filter=(TokenFilterFactory)((MultiTermAwareComponent)filter).getMultiTermComponent();
      result=filter.create(result);
    }
  }
  return result;
}
