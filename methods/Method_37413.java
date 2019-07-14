public Chalk256 bgGrayscale(final int index){
  startSequence(BG_CODES[index(index,232,256)]);
  endSequence(RESET);
  return _this();
}
