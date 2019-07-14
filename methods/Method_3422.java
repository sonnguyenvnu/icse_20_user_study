public String[] tag(POSInstance instance){
  instance.tagArray=new int[instance.featureMatrix.length];
  model.viterbiDecode(instance,instance.tagArray);
  return instance.tags(model.tagSet());
}
