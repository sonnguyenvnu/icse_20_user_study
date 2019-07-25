private int protect(int v){
  while (v >= NB_WORDS) {
    v-=NB_WORDS;
  }
  while (v < 0) {
    v+=NB_WORDS;
  }
  return v;
}
