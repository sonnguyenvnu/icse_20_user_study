/** 
 * Constructs a Uri from the template, substituting in the provided arguments. <p>Arguments whose corresponding identifiers are not present in the template will be ignored.
 * @param representationId The representation identifier.
 * @param segmentNumber The segment number.
 * @param bandwidth The bandwidth.
 * @param time The time as specified by the segment timeline.
 * @return The built Uri.
 */
public String buildUri(String representationId,long segmentNumber,int bandwidth,long time){
  StringBuilder builder=new StringBuilder();
  for (int i=0; i < identifierCount; i++) {
    builder.append(urlPieces[i]);
    if (identifiers[i] == REPRESENTATION_ID) {
      builder.append(representationId);
    }
 else     if (identifiers[i] == NUMBER_ID) {
      builder.append(String.format(Locale.US,identifierFormatTags[i],segmentNumber));
    }
 else     if (identifiers[i] == BANDWIDTH_ID) {
      builder.append(String.format(Locale.US,identifierFormatTags[i],bandwidth));
    }
 else     if (identifiers[i] == TIME_ID) {
      builder.append(String.format(Locale.US,identifierFormatTags[i],time));
    }
  }
  builder.append(urlPieces[identifierCount]);
  return builder.toString();
}
