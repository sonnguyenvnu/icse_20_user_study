public Context join(final Context o){
  return new Context(Math.min(getLine(),o.getLine()),Math.min(getCol(),o.getCol()),Math.max(getLineEnd(),o.getLineEnd()),Math.max(getColEnd(),o.getColEnd()));
}
