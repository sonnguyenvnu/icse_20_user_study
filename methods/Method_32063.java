/** 
 * Gets a debug representation of the object.
 */
public String toString(){
  return "ConverterManager[" + iInstantConverters.size() + " instant," + iPartialConverters.size() + " partial," + iDurationConverters.size() + " duration," + iPeriodConverters.size() + " period," + iIntervalConverters.size() + " interval]";
}
