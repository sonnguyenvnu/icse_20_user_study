/** 
 * Check if a given source file should be checked by rules in this RuleSet. A file should not be checked if there is an <code>exclude</code> pattern which matches the file, unless there is an <code>include</code> pattern which also matches the file. In other words, <code>include</code> patterns override <code>exclude</code> patterns.
 * @param file the source file to check
 * @return <code>true</code> if the file should be checked,<code>false</code> otherwise
 */
public boolean applies(File file){
  return file == null || filter.filter(file);
}
