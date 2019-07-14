private static Optional<String> appendMessage(DescriptorProtos.DescriptorProto message,List<Integer> path,String fullName){
  fullName+=appendNameComponent(message.getName());
  return path.size() > 2 ? append(message,path.subList(2,path.size()),fullName) : Optional.of(fullName);
}
