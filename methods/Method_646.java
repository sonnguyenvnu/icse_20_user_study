public FieldSerializer getFieldSerializer(long hash){
  PropertyNamingStrategy[] namingStrategies=null;
  if (this.hashArray == null) {
    namingStrategies=PropertyNamingStrategy.values();
    long[] hashArray=new long[sortedGetters.length * namingStrategies.length];
    int index=0;
    for (int i=0; i < sortedGetters.length; i++) {
      String name=sortedGetters[i].fieldInfo.name;
      hashArray[index++]=TypeUtils.fnv1a_64(name);
      for (int j=0; j < namingStrategies.length; j++) {
        String name_t=namingStrategies[j].translate(name);
        if (name.equals(name_t)) {
          continue;
        }
        hashArray[index++]=TypeUtils.fnv1a_64(name_t);
      }
    }
    Arrays.sort(hashArray,0,index);
    this.hashArray=new long[index];
    System.arraycopy(hashArray,0,this.hashArray,0,index);
  }
  int pos=Arrays.binarySearch(hashArray,hash);
  if (pos < 0) {
    return null;
  }
  if (hashArrayMapping == null) {
    if (namingStrategies == null) {
      namingStrategies=PropertyNamingStrategy.values();
    }
    short[] mapping=new short[hashArray.length];
    Arrays.fill(mapping,(short)-1);
    for (int i=0; i < sortedGetters.length; i++) {
      String name=sortedGetters[i].fieldInfo.name;
      int p=Arrays.binarySearch(hashArray,TypeUtils.fnv1a_64(name));
      if (p >= 0) {
        mapping[p]=(short)i;
      }
      for (int j=0; j < namingStrategies.length; j++) {
        String name_t=namingStrategies[j].translate(name);
        if (name.equals(name_t)) {
          continue;
        }
        int p_t=Arrays.binarySearch(hashArray,TypeUtils.fnv1a_64(name_t));
        if (p_t >= 0) {
          mapping[p_t]=(short)i;
        }
      }
    }
    hashArrayMapping=mapping;
  }
  int getterIndex=hashArrayMapping[pos];
  if (getterIndex != -1) {
    return sortedGetters[getterIndex];
  }
  return null;
}
