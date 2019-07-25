public void configure(Encoded.InCharset charset){
  Encoded encoded=new Encoded();
  encoded.configure(charset);
  stringGenerator=encoded;
}
