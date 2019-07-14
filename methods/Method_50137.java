private static String appendEnum(DescriptorProtos.EnumDescriptorProto enumDescriptor,List<Integer> path,String fullName){
  fullName+=appendNameComponent(enumDescriptor.getName());
  if (path.size() > 2) {
    fullName+=appendNameComponent(enumDescriptor.getValue(path.get(3)).getName());
  }
  return fullName;
}
