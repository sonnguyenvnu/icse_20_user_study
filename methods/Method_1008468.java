/** 
 * Create a pre-configured char filter that may not vary at all.
 */
public static PreConfiguredCharFilter singleton(String name,boolean useFilterForMultitermQueries,Function<Reader,Reader> create){
  return new PreConfiguredCharFilter(name,CachingStrategy.ONE,useFilterForMultitermQueries,(reader,version) -> create.apply(reader));
}
