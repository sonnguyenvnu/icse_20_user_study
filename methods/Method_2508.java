@Override public int build(TreeMap<String,V> keyValueMap){
  int size=keyValueMap.size();
  int[] indexArray=new int[size];
  valueArray=(V[])keyValueMap.values().toArray();
  List<String> keyList=new ArrayList<String>(size);
  int i=0;
  for (  Entry<String,V> entry : keyValueMap.entrySet()) {
    indexArray[i]=i;
    valueArray[i]=entry.getValue();
    keyList.add(entry.getKey());
    ++i;
  }
  build(keyList,indexArray);
  return 0;
}
