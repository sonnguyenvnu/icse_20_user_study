/** 
 * Entry point for decorating a source file.
 * @param path absolute file path
 * @param src file contents
 */
public List<StyleRun> addStyles(String path,String src) throws Exception {
  this.path=path;
  source=src;
  Module m=indexer.getAstForFile(path);
  if (m != null) {
    m.visit(this);
    highlightLexicalTokens();
  }
  return styles;
}
