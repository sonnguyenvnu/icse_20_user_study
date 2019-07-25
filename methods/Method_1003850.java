@Override public Operation remove(AsciiString sessionId){
  return Promise.<Long>async(d -> d.accept(connection.del(sessionId))).operation();
}
