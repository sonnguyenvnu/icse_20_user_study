@Override public void documentType(final DocumentType documentType){
  try {
    TagWriterUtil.writeDoctype(appendable,documentType.nodeValue,documentType.publicId,documentType.systemId);
  }
 catch (  IOException ioex) {
    throw new LagartoDOMException(ioex);
  }
}
