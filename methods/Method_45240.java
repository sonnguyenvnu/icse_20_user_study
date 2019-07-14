private Charset asCharset(final Charset charset){
  if (charset != null) {
    return charset;
  }
  return Charset.defaultCharset();
}
