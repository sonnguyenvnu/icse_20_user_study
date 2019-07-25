private ConsumeManageRequest deserialize(ByteBuf buf){
  String subject=PayloadHolderUtils.readString(buf);
  String consumerGroup=PayloadHolderUtils.readString(buf);
  int code=buf.readInt();
  ConsumeManageRequest request=new ConsumeManageRequest();
  request.setSubject(subject);
  request.setGroup(consumerGroup);
  request.setConsumerFromWhere(code);
  return request;
}
