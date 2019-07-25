@Override public Instance pipe(Instance carrier){
  String[] fields=carrier.getData().toString().split("\\s+");
  int numFields=fields.length;
  Object[] featureNames=new Object[numFields];
  double[] featureValues=new double[numFields];
  for (int i=0; i < numFields; i++) {
    if (fields[i].contains("=")) {
      String[] subFields=fields[i].split("=");
      featureNames[i]=subFields[0];
      featureValues[i]=Double.parseDouble(subFields[1]);
    }
 else {
      featureNames[i]=fields[i];
      featureValues[i]=1.0;
    }
  }
  carrier.setData(new FeatureVector(getDataAlphabet(),featureNames,featureValues));
  return carrier;
}
