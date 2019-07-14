/** 
 * Returns a Map containing data from a ByteString that is parsed using the Proto Descriptor. <p>The FileDescriptorSet must contain a message with the name "{operationName}Request". This message will be used to parse the ByteString, and the resulting message will be transformed and returned as a Map.
 */
public static Map<String,Object> decodeVariables(String operationName,FileDescriptorSet fileDescriptorSet,ByteString encodedRequest){
  try {
    FileDescriptor fileDescriptor=FileDescriptor.buildFrom(fileDescriptorSet.getFileList().get(0),new FileDescriptor[]{});
    Descriptor messageType=fileDescriptor.findMessageTypeByName(operationName + "Request");
    Message message=DynamicMessage.parseFrom(messageType,encodedRequest);
    return ProtoToMap.messageToMap(message);
  }
 catch (  DescriptorValidationException|InvalidProtocolBufferException e) {
    e.printStackTrace();
    throw new RuntimeException(e);
  }
}
