private void overwriteTag(List<CoreDictionary.Attribute> attributeList,String[] posArray){
  int id;
  if (attributeList != null) {
    id=0;
    for (    CoreDictionary.Attribute attribute : attributeList) {
      if (attribute != null)       posArray[id]=attribute.nature[0].toString();
      ++id;
    }
  }
}
