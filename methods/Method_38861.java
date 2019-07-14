@Override public void cdata(final CData cdata){
  String nodeValue=cdata.getNodeValue();
  try {
    TagWriterUtil.writeCData(appendable,nodeValue);
  }
 catch (  IOException ioex) {
    throw new LagartoDOMException(ioex);
  }
}
