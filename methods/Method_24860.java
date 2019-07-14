/** 
 * Ignore processing packages, java.*.*. etc.
 */
static private boolean ignorableImport(String packageName){
  return (packageName.startsWith("java.") || packageName.startsWith("javax."));
}
