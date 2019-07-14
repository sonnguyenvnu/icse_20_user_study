public FieldDeserializer smartMatch(String key,int[] setFlags){
  if (key == null) {
    return null;
  }
  FieldDeserializer fieldDeserializer=getFieldDeserializer(key,setFlags);
  if (fieldDeserializer == null) {
    long smartKeyHash=TypeUtils.fnv1a_64_lower(key);
    if (this.smartMatchHashArray == null) {
      long[] hashArray=new long[sortedFieldDeserializers.length];
      for (int i=0; i < sortedFieldDeserializers.length; i++) {
        hashArray[i]=TypeUtils.fnv1a_64_lower(sortedFieldDeserializers[i].fieldInfo.name);
      }
      Arrays.sort(hashArray);
      this.smartMatchHashArray=hashArray;
    }
    int pos=Arrays.binarySearch(smartMatchHashArray,smartKeyHash);
    boolean is=false;
    if (pos < 0 && (is=key.startsWith("is"))) {
      smartKeyHash=TypeUtils.fnv1a_64_lower(key.substring(2));
      pos=Arrays.binarySearch(smartMatchHashArray,smartKeyHash);
    }
    if (pos >= 0) {
      if (smartMatchHashArrayMapping == null) {
        short[] mapping=new short[smartMatchHashArray.length];
        Arrays.fill(mapping,(short)-1);
        for (int i=0; i < sortedFieldDeserializers.length; i++) {
          int p=Arrays.binarySearch(smartMatchHashArray,TypeUtils.fnv1a_64_lower(sortedFieldDeserializers[i].fieldInfo.name));
          if (p >= 0) {
            mapping[p]=(short)i;
          }
        }
        smartMatchHashArrayMapping=mapping;
      }
      int deserIndex=smartMatchHashArrayMapping[pos];
      if (deserIndex != -1) {
        if (!isSetFlag(deserIndex,setFlags)) {
          fieldDeserializer=sortedFieldDeserializers[deserIndex];
        }
      }
    }
    if (fieldDeserializer != null) {
      FieldInfo fieldInfo=fieldDeserializer.fieldInfo;
      if ((fieldInfo.parserFeatures & Feature.DisableFieldSmartMatch.mask) != 0) {
        return null;
      }
      Class fieldClass=fieldInfo.fieldClass;
      if (is && (fieldClass != boolean.class && fieldClass != Boolean.class)) {
        fieldDeserializer=null;
      }
    }
  }
  return fieldDeserializer;
}
