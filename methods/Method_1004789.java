/** 
 * Seeks the field with the given name in the stream and positions (and returns) the parser to the next available token (value or not). Return null if no token is found.
 * @param path
 * @param parser
 * @return token associated with the given path or null if not found
 */
public static Token seek(Parser parser,String path){
  if (!StringUtils.hasText(path)) {
    return null;
  }
  List<String> tokens=StringUtils.tokenize(path,".");
  return seek(parser,tokens.toArray(new String[tokens.size()]));
}
