@Override public void outgoing(Socket socket,ByteBuffer bytes){
  try {
    responseBuilder.append(decoder.decode(bytes));
  }
 catch (  CharacterCodingException e) {
    notifier().error("Problem decoding network traffic",e);
  }
}
