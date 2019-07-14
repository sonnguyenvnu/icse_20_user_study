public String[] recognize(NERInstance instance){
  instance.tagArray=new int[instance.size()];
  model.viterbiDecode(instance);
  return instance.tags(tagSet);
}
