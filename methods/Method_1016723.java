public Classification classify(Instance instance){
  FeatureVector fv=(FeatureVector)instance.getData();
  assert (instancePipe == null || fv.getAlphabet() == this.instancePipe.getDataAlphabet());
  Node leaf=getLeaf(m_root,fv);
  return new Classification(instance,this,leaf.getGainRatio().getBaseLabelDistribution());
}
