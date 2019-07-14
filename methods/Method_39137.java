@Override public boolean init(final String actionPath,final String[] separators){
  boolean hasMacros=super.init(actionPath,separators);
  if (hasMacros) {
    regexpPattern=new Pattern[macrosCount];
  }
  return hasMacros;
}
