@Override public void visit(final Cookie cookie){
  cookie.setMaxAge(unit.toSeconds(maxAge));
}
