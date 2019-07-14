@Override public void cdata(final CharSequence cdata){
  try {
    TagWriterUtil.writeCData(appendable,cdata);
  }
 catch (  IOException ioex) {
    throw new LagartoException(ioex);
  }
}
