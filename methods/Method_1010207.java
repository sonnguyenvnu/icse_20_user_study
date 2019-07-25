private void notify(byte[] message){
  final ByteBuffer bb=ByteBuffer.allocate(myHeader.length + message.length + 8 + 1);
  bb.put(myHeader);
  bb.put((byte)0);
  bb.putLong(Thread.currentThread().getId());
  bb.put(message);
  final byte[] array=bb.array();
  for (  ClientToken client : myClients) {
    client.sendToClient(array);
  }
}
