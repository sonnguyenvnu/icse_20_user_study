@Override public void objectToEntry(WebURL url,TupleOutput output){
  output.writeString(url.getURL());
  output.writeInt(url.getDocid());
  output.writeInt(url.getParentDocid());
  output.writeString(url.getParentUrl());
  output.writeShort(url.getDepth());
  output.writeByte(url.getPriority());
  output.writeString(url.getAnchor());
}
