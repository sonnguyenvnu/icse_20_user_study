static public boolean isNonBlankData(Object o){
  return o != null && !(o instanceof EvalError) && (!(o instanceof String) || ((String)o).length() > 0);
}
