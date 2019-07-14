@Override public void run(){
  while (start <= end) {
    int f=flag;
    if ((start & 0x01) == f) {
      System.out.println(name + "+-+" + start);
      start+=2;
      flag^=0x1;
    }
  }
}
