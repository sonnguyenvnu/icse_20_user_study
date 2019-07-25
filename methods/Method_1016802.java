/** 
 * Converts each instance containing a FeatureVectorSequence to multiple instances,  each containing an AugmentableFeatureVector as data.  
 * @param ilist Instances with FeatureVectorSequence as data field
 * @param alphabetsPipe a Noop pipe containing the data and target alphabets for the resulting InstanceList 
 * @return an InstanceList where each Instance contains one Token's AugmentableFeatureVector as data 
 */
public static InstanceList convert(InstanceList ilist,Noop alphabetsPipe){
  if (ilist == null)   return null;
  InstanceList ret=new InstanceList(alphabetsPipe);
  for (  Instance inst : ilist)   ret.add(inst);
  return ret;
}
