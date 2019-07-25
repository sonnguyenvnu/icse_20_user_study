/** 
 * Create a pre-configured token filter that may not vary at all.
 */
public static PreConfiguredTokenFilter singleton(String name,boolean useFilterForMultitermQueries,Function<TokenStream,TokenStream> create){
  return new PreConfiguredTokenFilter(name,useFilterForMultitermQueries,CachingStrategy.ONE,(tokenStream,version) -> create.apply(tokenStream));
}
