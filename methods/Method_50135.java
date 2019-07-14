/** 
 * Iterate through a component's path inside a proto descriptor. <p>The path is a tuple of component type and a relative position For example: [4, 1, 3, 2, 2, 1] or [MESSAGE_TYPE_FIELD_NUMBER, 1, NESTED_TYPE_FIELD_NUMBER, 2, FIELD_FIELD_NUMBER, 1] is representing the second field of the third nested message in the second message in the file
 * @see DescriptorProtos.SourceCodeInfoOrBuilder for more info
 * @param descriptor proto file descriptor
 * @param path path of the element
 * @return full element's path as a string
 */
private static Optional<String> getFullName(DescriptorProtos.FileDescriptorProto descriptor,List<Integer> path){
  String fullName=descriptor.getPackage();
switch (path.get(0)) {
case DescriptorProtos.FileDescriptorProto.ENUM_TYPE_FIELD_NUMBER:
    DescriptorProtos.EnumDescriptorProto enumDescriptor=descriptor.getEnumType(path.get(1));
  return Optional.of(appendEnum(enumDescriptor,path,fullName));
case DescriptorProtos.FileDescriptorProto.MESSAGE_TYPE_FIELD_NUMBER:
DescriptorProtos.DescriptorProto message=descriptor.getMessageType(path.get(1));
return appendMessage(message,path,fullName);
case DescriptorProtos.FileDescriptorProto.SERVICE_FIELD_NUMBER:
DescriptorProtos.ServiceDescriptorProto serviceDescriptor=descriptor.getService(path.get(1));
fullName+=appendNameComponent(serviceDescriptor.getName());
if (path.size() > 2) {
fullName+=appendNameComponent(serviceDescriptor.getMethod(path.get(3)).getName());
}
return Optional.of(fullName);
default :
return Optional.empty();
}
}
