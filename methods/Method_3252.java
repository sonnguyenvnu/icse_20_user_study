boolean loadDat(String path){
  ByteArray byteArray=ByteArray.createByteArray(path + Predefine.BIN_EXT);
  if (byteArray == null)   return false;
  int size=byteArray.nextInt();
  Attribute[] attributeArray=new Attribute[size];
  for (int i=0; i < attributeArray.length; ++i) {
    int length=byteArray.nextInt();
    Attribute attribute=new Attribute(length);
    for (int j=0; j < attribute.dependencyRelation.length; ++j) {
      attribute.dependencyRelation[j]=byteArray.nextString();
      attribute.p[j]=byteArray.nextFloat();
    }
    attributeArray[i]=attribute;
  }
  return trie.load(byteArray,attributeArray);
}
