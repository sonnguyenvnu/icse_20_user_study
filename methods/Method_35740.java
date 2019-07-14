public static InputStreamSource forString(final String string,final Charset charset){
  return new InputStreamSource(){
    @Override public InputStream getStream(){
      return string == null ? null : new ByteArrayInputStream(Strings.bytesFromString(string,charset));
    }
  }
;
}
