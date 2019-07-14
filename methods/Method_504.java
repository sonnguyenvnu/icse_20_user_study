public void config(Feature feature,boolean state){
  features=Feature.config(features,feature,state);
  if ((features & Feature.InitStringFieldAsEmpty.mask) != 0) {
    stringDefaultValue="";
  }
}
