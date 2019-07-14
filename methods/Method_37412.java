/** 
 * Colors with red-green-blue value, in the range 0 to 6.
 */
public Chalk256 bgRgb(final int r,final int b,final int g){
  startSequence(BG_CODES[index(36 * r + 6 * g + b,16,232)]);
  endSequence(RESET);
  return _this();
}
