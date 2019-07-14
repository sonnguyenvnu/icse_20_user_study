/** 
 * Encodes the data portion of an ExecutionResult as ByteString. <p>The FileDescriptorSet must contain a message with the name "{operationName}Response". This message will be populated with data from the execution result and encoded as a ByteString.
 */
public static ByteString encodeResponse(String operationName,FileDescriptorSet fileDescriptorSet,ExecutionResult executionResult){
  try {
    FileDescriptor fileDescriptor=FileDescriptor.buildFrom(fileDescriptorSet.getFileList().get(0),new FileDescriptor[]{});
    Descriptor messageType=fileDescriptor.findMessageTypeByName(operationName + "Response");
    Message message=DynamicMessage.parseFrom(messageType,ByteString.EMPTY);
    Message responseData=QueryResponseToProto.buildMessage(message,executionResult.getData());
    return responseData.toByteString();
  }
 catch (  DescriptorValidationException|InvalidProtocolBufferException e) {
    e.printStackTrace();
    throw new RuntimeException(e);
  }
}
