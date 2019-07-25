protected void transform(byte[] in,int offset){
  int[] result=sha(h0,h1,h2,h3,h4,h5,h6,h7,in,offset);
  h0=result[0];
  h1=result[1];
  h2=result[2];
  h3=result[3];
  h4=result[4];
  h5=result[5];
  h6=result[6];
  h7=result[7];
}
