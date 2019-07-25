/** 
 * This method is deprecated. 
 */
@Deprecated public Sequence[] predict(InstanceList testing){
  testing.setFeatureSelection(this.globalFeatureSelection);
  for (int i=0; i < featureInducers.size(); i++) {
    FeatureInducer klfi=(FeatureInducer)featureInducers.get(i);
    klfi.induceFeaturesFor(testing,false,false);
  }
  Sequence[] ret=new Sequence[testing.size()];
  for (int i=0; i < testing.size(); i++) {
    Instance instance=testing.get(i);
    Sequence input=(Sequence)instance.getData();
    Sequence trueOutput=(Sequence)instance.getTarget();
    assert (input.size() == trueOutput.size());
    Sequence predOutput=new MaxLatticeDefault(this,input).bestOutputSequence();
    assert (predOutput.size() == trueOutput.size());
    ret[i]=predOutput;
  }
  return ret;
}
