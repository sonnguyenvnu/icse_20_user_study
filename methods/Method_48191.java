private String staticBuffer2String(final StaticBuffer s){
  return new String(s.as(StaticBuffer.ARRAY_FACTORY),Charset.forName("UTF-8"));
}
