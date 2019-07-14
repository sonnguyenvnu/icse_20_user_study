/** 
 * Returns a new  {@link ObjectToSourceTransformer}, which uses a  {@link MoshiBufferedSourceAdapter} to parse fromobjects of the specified type.
 */
@Nonnull @Experimental public static <Parsed>ObjectToSourceTransformer<Parsed> createObjectToSourceTransformer(@Nonnull Type type){
  return new ObjectToSourceTransformer<>(new MoshiBufferedSourceAdapter<Parsed>(new Moshi.Builder().build(),type));
}
