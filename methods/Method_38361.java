/** 
 * Count escapes to the left.
 */
protected int countEscapes(final String template,int macroIndex){
  macroIndex--;
  int escapeCount=0;
  while (macroIndex >= 0) {
    if (template.charAt(macroIndex) != ESCAPE_CHARACTER) {
      break;
    }
    escapeCount++;
    macroIndex--;
  }
  return escapeCount;
}
