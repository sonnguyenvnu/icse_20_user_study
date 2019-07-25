/** 
 * @param inst input instance, with FeatureVectorSequence as data.
 * @param alphabetsPipe a Noop pipe containing the data and target alphabets for the resulting InstanceList and AugmentableFeatureVectors
 * @return list of instances, each with one AugmentableFeatureVector as data
 */
public static InstanceList convert(Instance inst,Noop alphabetsPipe){
  InstanceList ret=new InstanceList(alphabetsPipe);
  Object obj=inst.getData();
  assert (obj instanceof FeatureVectorSequence);
  FeatureVectorSequence fvs=(FeatureVectorSequence)obj;
  LabelSequence ls=(LabelSequence)inst.getTarget();
  assert (fvs.size() == ls.size());
  Object instName=(inst.getName() == null ? "NONAME" : inst.getName());
  for (int j=0; j < fvs.size(); j++) {
    FeatureVector fv=fvs.getFeatureVector(j);
    int[] indices=fv.getIndices();
    FeatureVector data=new AugmentableFeatureVector(alphabetsPipe.getDataAlphabet(),indices,fv.getValues(),indices.length);
    Labeling target=ls.getLabelAtPosition(j);
    String name=instName.toString() + "_@_POS_" + (j + 1);
    Object source=inst.getSource();
    Instance toAdd=alphabetsPipe.pipe(new Instance(data,target,name,source));
    ret.add(toAdd);
  }
  return ret;
}
