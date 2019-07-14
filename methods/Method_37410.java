public Chalk256 bgBright(final int index){
  startSequence(BG_CODES[index(index,8,16)]);
  endSequence(RESET);
  return _this();
}
