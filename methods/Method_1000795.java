public static RemountBytes create(byte[] bs){
  int[] fails=new int[bs.length];
  if (bs.length > 1) {
    for (int i=2; i < bs.length; i++) {
      int blueL=1;
      int blueR=i - 1;
      int x=0;
      for (int j=blueL; j <= blueR; j++) {
        byte red=bs[x];
        byte blue=bs[j];
        if (red == blue)         x++;
 else         x=0;
      }
      fails[i]=x;
    }
  }
  RemountBytes re=new RemountBytes();
  re.bytes=bs;
  re.fails=fails;
  return re;
}
