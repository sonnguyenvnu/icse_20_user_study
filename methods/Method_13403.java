/** 
 * Return the more specific of the acceptable and the producible media types with the q-value of the former.
 */
private MediaType getMostSpecificMediaType(MediaType acceptType,MediaType produceType){
  MediaType produceTypeToUse=produceType.copyQualityValue(acceptType);
  return (MediaType.SPECIFICITY_COMPARATOR.compare(acceptType,produceTypeToUse) <= 0 ? acceptType : produceTypeToUse);
}
