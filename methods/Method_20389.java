private void removeMethodIfDuplicatedBySetter(Collection<AttributeInfo> attributeInfos){
  for (  AttributeInfo attributeInfo : attributeInfos) {
    Iterator<MethodInfo> iterator=methodsReturningClassType.iterator();
    while (iterator.hasNext()) {
      MethodInfo methodInfo=iterator.next();
      if (methodInfo.name.equals(attributeInfo.getFieldName()) && methodInfo.params.size() == 1 && methodInfo.params.get(0).type.equals(attributeInfo.getTypeName())) {
        iterator.remove();
      }
    }
  }
}
