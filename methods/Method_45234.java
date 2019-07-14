public final InputStream toInputStream(){
  return new ByteArrayInputStream(this.content);
}
