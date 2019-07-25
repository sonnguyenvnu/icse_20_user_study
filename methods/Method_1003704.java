/** 
 * Creates a URL by appending the given <i>path</i> to the public address
 * @param path the path to append to the public address
 * @return the public address with the given path appended
 * @since 1.2
 */
default URI get(String path){
  return builder().path(path).build();
}
