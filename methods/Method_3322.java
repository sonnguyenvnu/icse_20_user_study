@Override public boolean load(ByteArray byteArray){
  if (!super.load(byteArray))   return false;
  int size=byteArray.nextInt();
  featureTemplateArray=new FeatureTemplate[size];
  for (int i=0; i < size; ++i) {
    FeatureTemplate featureTemplate=new FeatureTemplate();
    featureTemplate.load(byteArray);
    featureTemplateArray[i]=featureTemplate;
  }
  if (!byteArray.hasMore())   byteArray.close();
  return true;
}
