public void changeFeatureWeight(HashMap<Object,CompactArray> map,HashMap<Object,CompactArray> aMap,Object featureName,int labelIndex,float change,int size){
  CompactArray values=map.get(featureName);
  CompactArray aValues;
  if (values != null) {
    values.set(labelIndex,change);
    aValues=aMap.get(featureName);
    aValues.set(labelIndex,iteration * change);
  }
 else {
    float[] val=new float[]{change};
    values=new CompactArray(labelIndex,val);
    map.put(featureName,values);
    float[] aVal=new float[]{iteration * change};
    aValues=new CompactArray(labelIndex,aVal);
    aMap.put(featureName,aValues);
  }
}
