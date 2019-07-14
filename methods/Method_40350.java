/** 
 * Parse a file.  Does not look in the cache or cache the result.
 */
private Module parse(String path) throws Exception {
  fine("parsing " + path);
  mod ast=invokeANTLR(path);
  return generateAST(ast,path);
}
