static void expand(Object array){
  for (int i=64; i < 65536; i=i + i) {
    System.arraycopy(array,0,array,i,i);
  }
}
