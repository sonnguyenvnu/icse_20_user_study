/** 
 * Get a string describing a location. Format: class.method:translated_line_number
 * @param loc a location
 * @return descriptive string for the given location
 */
protected String locationToString(Location loc){
  LineID line=locationToLineID(loc);
  int lineNumber=(line != null) ? (line.lineIdx() + 1) : loc.lineNumber();
  return loc.declaringType().name() + "." + loc.method().name() + ":" + lineNumber;
}
