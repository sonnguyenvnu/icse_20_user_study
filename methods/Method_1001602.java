@Override public void fill(int windingRule){
  if (macroInProgress != null) {
    closeMacro();
  }
  if (windingRule == PathIterator.WIND_EVEN_ODD) {
    append("eofill",true);
  }
 else   if (windingRule == PathIterator.WIND_NON_ZERO) {
    append("fill",true);
  }
}
