public Chalk256 bgStandard(final int index){
  startSequence(BG_CODES[index(index,0,8)]);
  endSequence(RESET);
  return _this();
}
