protected String searchTypeHavingMember(String typeName,String name,String descriptor,List<Container.Entry> entries){
  for (  Container.Entry entry : entries) {
    Type type=api.getTypeFactory(entry).make(api,entry,typeName);
    if (type != null) {
      if (descriptor.indexOf('(') == -1) {
        for (        Type.Field field : type.getFields()) {
          if (field.getName().equals(name) && DescriptorMatcher.matchFieldDescriptors(field.getDescriptor(),descriptor)) {
            return typeName;
          }
        }
      }
 else {
        for (        Type.Method method : type.getMethods()) {
          if (method.getName().equals(name) && DescriptorMatcher.matchMethodDescriptors(method.getDescriptor(),descriptor)) {
            return typeName;
          }
        }
      }
      String typeOwnerName=searchTypeHavingMember(type.getSuperName(),name,descriptor,entry);
      if (typeOwnerName != null) {
        return typeOwnerName;
      }
    }
  }
  return null;
}
