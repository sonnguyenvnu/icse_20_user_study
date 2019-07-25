/** 
 * Parses the coverage information item from a string
 * @param outputMessage
 * @param modelName the name of the model for which this is coverage information
 * @return
 */
public static CoverageInformationItem parse(String outputMessage,String modelName){
  outputMessage=outputMessage.trim();
  final int layer=outputMessage.lastIndexOf(TLCGlobals.coverageIndent) + 1;
  int index=outputMessage.indexOf(COLON);
  return new CoverageInformationItem(Location.parseLocation(outputMessage.substring(layer,index)),Long.parseLong(outputMessage.substring(index + COLON.length())),modelName,layer);
}
