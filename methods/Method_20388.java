void addAttributes(Collection<AttributeInfo> attributesToAdd){
  removeMethodIfDuplicatedBySetter(attributesToAdd);
  for (  AttributeInfo info : attributesToAdd) {
    int existingIndex=attributeInfo.indexOf(info);
    if (existingIndex > -1) {
      attributeInfo.set(existingIndex,info);
    }
 else {
      attributeInfo.add(info);
    }
  }
}
