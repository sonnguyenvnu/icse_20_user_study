private void ensureLength(){
  if (attributesCount + 1 >= attrNames.length) {
    attrNames=ArraysUtil.resize(attrNames,attributesCount * 2);
    attrValues=ArraysUtil.resize(attrValues,attributesCount * 2);
  }
}
