public void scriptExists(String... sha1){
  final byte[][] bsha1=new byte[sha1.length][];
  for (int i=0; i < bsha1.length; i++) {
    bsha1[i]=SafeEncoder.encode(sha1[i]);
  }
  scriptExists(bsha1);
}
