private static ImmutableMap<String,String> getCommentsFromDescriptorFile(){
  try {
    InputStream is=DescriptorSet.class.getClassLoader().getResourceAsStream(DEFAULT_DESCRIPTOR_SET_FILE_LOCATION);
    DescriptorProtos.FileDescriptorSet descriptors=DescriptorProtos.FileDescriptorSet.parseFrom(is);
    return descriptors.getFileList().stream().flatMap(fileDescriptorProto -> parseDescriptorFile(fileDescriptorProto).entrySet().stream()).collect(ImmutableMap.toImmutableMap(Map.Entry::getKey,Map.Entry::getValue,(entry,value) -> entry));
  }
 catch (  IOException ignored) {
  }
  return ImmutableMap.of();
}
