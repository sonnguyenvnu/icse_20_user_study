private static Base64Encoder getInstance(){
  if (encoder == null) {
synchronized (Encoding.class) {
      if (encoder == null) {
        encoder=new GuavaBase64Encoder();
      }
    }
  }
  return encoder;
}
