/** 
 * Helper function that creates a StringList of the compatible Modes for this Contribution.
 */
static StringList parseModeList(StringDict properties){
  String unparsedModes=properties.get(MODES_PROPERTY);
  if ("null".equals(unparsedModes)) {
    properties.remove(MODES_PROPERTY);
    unparsedModes=null;
  }
  StringList outgoing=new StringList();
  if (unparsedModes != null) {
    outgoing.append(PApplet.trim(PApplet.split(unparsedModes,',')));
  }
  return outgoing;
}
