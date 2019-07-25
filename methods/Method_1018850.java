/** 
 * Attempt to match all required files in a particular directory. <p> The directory is <em>not</em> searched <em>recursively</em>.
 * @param directoryName name of the directory to search
 * @return <code>true</code> if all required files were found; <code>false</code> otherwise
 */
private boolean find(String directoryName){
  File dir=new File(directoryName);
  if (!dir.exists()) {
    return false;
  }
  File[] files=dir.listFiles();
  if (files != null) {
    Set<String> matches=new HashSet<String>(patternsToMatch.length);
    for (    File file : files) {
      for (      Pattern pattern : patternsToMatch) {
        Matcher matcher=pattern.matcher(file.getName());
        if (matcher.matches()) {
          matches.add(pattern.pattern());
          if (matches.size() == patternsToMatch.length) {
            return true;
          }
        }
      }
    }
  }
  return false;
}
