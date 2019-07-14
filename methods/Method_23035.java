/** 
 * Parse a String array that contains attribute/value pairs separated by = (the equals sign). The # (hash) symbol is used to denote comments. Comments can be anywhere on a line. Blank lines are ignored. In 3.0a6, no longer taking a blank HashMap as param; no cases in the main PDE code of adding to a (Hash)Map. Also returning the Map instead of void. Both changes modify the method signature, but this was only used by the contrib classes.
 */
static public StringDict readSettings(String filename,String[] lines){
  StringDict settings=new StringDict();
  for (  String line : lines) {
    int commentMarker=line.indexOf('#');
    if (commentMarker != -1) {
      line=line.substring(0,commentMarker);
    }
    line=line.trim();
    if (line.length() != 0) {
      int equals=line.indexOf('=');
      if (equals == -1) {
        if (filename != null) {
          System.err.println("Ignoring illegal line in " + filename);
          System.err.println("  " + line);
        }
      }
 else {
        String attr=line.substring(0,equals).trim();
        String valu=line.substring(equals + 1).trim();
        settings.set(attr,valu);
      }
    }
  }
  return settings;
}
