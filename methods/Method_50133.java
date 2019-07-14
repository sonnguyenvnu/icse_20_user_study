private static Map<String,String> parseDescriptorFile(DescriptorProtos.FileDescriptorProto descriptor){
  return descriptor.getSourceCodeInfo().getLocationList().stream().filter(location -> !(location.getLeadingComments().isEmpty() && location.getTrailingComments().isEmpty())).map(location -> getFullName(descriptor,location.getPathList()).map(fullName -> new AbstractMap.SimpleImmutableEntry<>(fullName,constructComment(location)))).filter(Optional::isPresent).map(Optional::get).collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue));
}
