/** 
 * @param out
 * @param programImports
 * @param codeFolderImports
 * @return the header offset
 */
protected int writeImports(final PrintWriter out,final List<String> programImports,final List<String> codeFolderImports){
  int count=writeImportList(out,getCoreImports());
  count+=writeImportList(out,programImports);
  count+=writeImportList(out,codeFolderImports);
  count+=writeImportList(out,getDefaultImports());
  return count;
}
