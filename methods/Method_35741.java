public static InputStreamSource forBytes(final byte[] bytes){
  return new InputStreamSource(){
    @Override public InputStream getStream(){
      return bytes == null ? null : new ByteArrayInputStream(bytes);
    }
  }
;
}
