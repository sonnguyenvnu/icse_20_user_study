public void wrap(long[] b){
  bitmap=b;
  for (x=0; x < bitmap.length; ++x) {
    if ((w=bitmap[x]) != 0) {
      break;
    }
  }
}
