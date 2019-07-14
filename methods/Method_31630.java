/** 
 * Checks whether this filename is actually a sql-based callback instead of a regular migration.
 * @param filename  The filename to check.
 * @param separator The separator to use.
 * @param suffixes  The sql migration suffixes.
 * @return {@code true} if it is, {@code false} if it isn't.
 */
static boolean isSqlCallback(String filename,String separator,String... suffixes){
  for (  String suffix : suffixes) {
    String baseName=filename.substring(0,filename.length() - suffix.length());
    int index=baseName.indexOf(separator);
    if (index >= 0) {
      baseName=baseName.substring(0,index);
    }
    if (Event.fromId(baseName) != null) {
      return true;
    }
  }
  return false;
}
