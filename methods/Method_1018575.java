public static void blockxor(byte[] S,int Si,byte[] D,int Di,int len){
  for (int i=0; i < len; i++) {
    D[Di + i]^=S[Si + i];
  }
}
