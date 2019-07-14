@Override public WebURL entryToObject(TupleInput input){
  WebURL webURL=new WebURL();
  webURL.setURL(input.readString());
  webURL.setDocid(input.readInt());
  webURL.setParentDocid(input.readInt());
  webURL.setParentUrl(input.readString());
  webURL.setDepth(input.readShort());
  webURL.setPriority(input.readByte());
  webURL.setAnchor(input.readString());
  return webURL;
}
