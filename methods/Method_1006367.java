void wrap(long[] b){
  bitmap=b;
  for (position=bitmap.length - 1; position >= 0; --position) {
    if ((word=bitmap[position]) != 0) {
      break;
    }
  }
}
