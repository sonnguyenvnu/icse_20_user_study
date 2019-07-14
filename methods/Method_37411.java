public Chalk256 bgRgb(final int index){
  startSequence(BG_CODES[index(index,16,232)]);
  endSequence(RESET);
  return _this();
}
