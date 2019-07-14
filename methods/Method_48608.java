private String[][] getKeyStatus(PropertyKey[] keys,JanusGraphIndex index){
  String[][] keyStatus=new String[keys.length][2];
  for (int i=0; i < keys.length; i++) {
    keyStatus[i][0]=keys[i].name();
    keyStatus[i][1]=index.getIndexStatus(keys[i]).name();
  }
  return keyStatus.length > 0 ? keyStatus : new String[][]{{"",""}};
}
