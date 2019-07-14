private void writePreviousMessageData(TLRPC.Message message,SerializedData data){
  message.media.serializeToStream(data);
  data.writeString(message.message != null ? message.message : "");
  data.writeString(message.attachPath != null ? message.attachPath : "");
  int count;
  data.writeInt32(count=message.entities.size());
  for (int a=0; a < count; a++) {
    message.entities.get(a).serializeToStream(data);
  }
}
