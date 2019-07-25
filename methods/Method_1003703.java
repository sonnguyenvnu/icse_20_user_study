/** 
 * Creates a URL by appending the given <i>path</i> to the public address
 * @param ctx the handling context at the time the public address is needed
 * @param path the path to append to the public address
 * @return the public address with the given path appended
 * @deprecated since 1.2, use {@link #get(String)}
 */
@Deprecated default URI get(@SuppressWarnings("UnusedParameters") Context ctx,String path){
  return builder().path(path).build();
}
