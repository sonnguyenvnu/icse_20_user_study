public boolean hasInlineBody(){
  return !BinaryFile.class.isAssignableFrom(bodyStreamSource.getClass());
}
