public static WeixinMessageTransfer parser(WeixinRequest request) throws RuntimeException {
  try {
    XMLReader xmlReader=XMLReaderFactory.createXMLReader();
    xmlReader.setContentHandler(global);
    xmlReader.parse(new InputSource(new ByteArrayInputStream(request.getOriginalContent().getBytes(ServerToolkits.UTF_8))));
  }
 catch (  IOException e) {
    throw new RuntimeException(e);
  }
catch (  SAXException e) {
    throw new RuntimeException(e);
  }
  return new WeixinMessageTransfer(request.getAesToken(),request.getEncryptType(),global.toUserName,global.fromUserName,global.getAccountType(),global.msgType,global.eventType,global.nodeNames);
}
