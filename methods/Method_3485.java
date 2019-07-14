/** 
 * ?????????nature
 * @param nature ??
 * @return ??????????????????????
 */
public boolean confirmNature(Nature nature){
  if (attribute.nature.length == 1 && attribute.nature[0] == nature) {
    return true;
  }
  boolean result=true;
  int frequency=attribute.getNatureFrequency(nature);
  if (frequency == 0) {
    frequency=1000;
    result=false;
  }
  attribute=new CoreDictionary.Attribute(nature,frequency);
  return result;
}
