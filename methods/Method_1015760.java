public static void parse(InputStream input,BiConsumer<Short,Message> msg_consumer,BiConsumer<Short,MessageBatch> batch_consumer,boolean tcp){
  if (msg_consumer == null && batch_consumer == null)   return;
  byte[] tmp=new byte[Global.INT_SIZE];
  try (DataInputStream dis=new DataInputStream(input)){
    for (; ; ) {
      if (tcp) {
        dis.readFully(tmp);
        if (Arrays.equals(Connection.cookie,tmp)) {
          dis.readShort();
          dis.readShort();
          IpAddress peer=new IpAddress();
          peer.readFrom(dis);
          continue;
        }
 else {
        }
      }
      short version=dis.readShort();
      byte flags=dis.readByte();
      boolean is_message_list=(flags & LIST) == LIST;
      boolean multicast=(flags & MULTICAST) == MULTICAST;
      if (is_message_list) {
        MessageBatch[] batches=Util.readMessageBatch(dis,multicast);
        for (        MessageBatch batch : batches) {
          if (batch == null)           continue;
          if (batch_consumer != null)           batch_consumer.accept(version,batch);
 else {
            for (            Message msg : batch)             msg_consumer.accept(version,msg);
          }
        }
      }
 else {
        Message msg=Util.readMessage(dis);
        if (msg_consumer != null)         msg_consumer.accept(version,msg);
      }
    }
  }
 catch (  EOFException ignored) {
  }
catch (  Throwable t) {
    t.printStackTrace();
  }
}
