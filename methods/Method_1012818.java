@Override public void write(int b) throws IOException {
  if (b == 10) {
    byte text[]=new byte[pos];
    System.arraycopy(line,0,text,0,pos);
    LOGGER.info(new String(text,StandardCharsets.UTF_8));
    pos=0;
    line=new byte[5000];
  }
 else   if (b != 13) {
    line[pos]=(byte)b;
    pos++;
  }
}
