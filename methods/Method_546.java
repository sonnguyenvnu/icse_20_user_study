public final void scanIdent(){
  np=bp - 1;
  hasSpecial=false;
  for (; ; ) {
    sp++;
    next();
    if (Character.isLetterOrDigit(ch)) {
      continue;
    }
    String ident=stringVal();
    if ("null".equalsIgnoreCase(ident)) {
      token=JSONToken.NULL;
    }
 else     if ("new".equals(ident)) {
      token=JSONToken.NEW;
    }
 else     if ("true".equals(ident)) {
      token=JSONToken.TRUE;
    }
 else     if ("false".equals(ident)) {
      token=JSONToken.FALSE;
    }
 else     if ("undefined".equals(ident)) {
      token=JSONToken.UNDEFINED;
    }
 else     if ("Set".equals(ident)) {
      token=JSONToken.SET;
    }
 else     if ("TreeSet".equals(ident)) {
      token=JSONToken.TREE_SET;
    }
 else {
      token=JSONToken.IDENTIFIER;
    }
    return;
  }
}
