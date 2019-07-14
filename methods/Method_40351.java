/** 
 * Parse a string.  Does not look in the cache or cache the result.
 */
private Module parse(String path,String contents) throws Exception {
  fine("parsing " + path);
  mod ast=invokeANTLR(path,contents);
  return generateAST(ast,path);
}
