/** 
 * Builds a list of file names that could be useful to trace the duplicate key source. Adds spec and part definition classes and excludes blacklisted file names. Doesn't add the same file name in a row since that's not useful.
 */
private String getDuplicateKeyMessage(){
  StackTraceElement[] stackTrace=Thread.currentThread().getStackTrace();
  List<String> specHierarchy=new ArrayList<>();
  for (int i=0, len=stackTrace.length; i < len; i++) {
    final StackTraceElement stackElement=stackTrace[i];
    final String fileName=stackElement.getFileName();
    if (fileName == null) {
      continue;
    }
    final boolean hasJustBeenAdded=!specHierarchy.isEmpty() && specHierarchy.get(specHierarchy.size() - 1).equals(fileName);
    if (hasMatch(fileName) && !mLogger.getKeyCollisionStackTraceBlacklist().contains(fileName) && !hasJustBeenAdded) {
      specHierarchy.add(fileName);
    }
  }
  if (specHierarchy.isEmpty()) {
    return STACK_TRACE_NO_SPEC_MESSAGE;
  }
  return format(specHierarchy);
}
