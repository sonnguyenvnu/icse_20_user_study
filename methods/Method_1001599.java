public void fill(int windingRule){
  append("%fill",true);
  if (windingRule == PathIterator.WIND_EVEN_ODD) {
    append("eofill",true);
  }
 else   if (windingRule == PathIterator.WIND_NON_ZERO) {
    append("fill",true);
  }
}
