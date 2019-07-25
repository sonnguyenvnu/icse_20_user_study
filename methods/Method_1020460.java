/** 
 * Process the content of a file for converting mangled J2CL names to minified (but still pretty and unique) versions and strips block comments.
 */
public String minify(String content){
  return minify(null,content);
}
