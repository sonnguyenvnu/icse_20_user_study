/** 
 * Retains only the specified formatting.
 * @param retention the formatting to retain
 */
public void retain(FormatRetention retention){
  if (retention == FormatRetention.FORMATTING || retention == FormatRetention.NONE) {
    setClickEvent(null);
    setHoverEvent(null);
  }
  if (retention == FormatRetention.EVENTS || retention == FormatRetention.NONE) {
    setColor(null);
    setBold(null);
    setItalic(null);
    setUnderlined(null);
    setStrikethrough(null);
    setObfuscated(null);
    setInsertion(null);
  }
}
