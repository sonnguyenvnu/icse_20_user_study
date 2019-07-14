public final void scanNumber(){
  np=bp;
  if (ch == '-') {
    sp++;
    next();
  }
  for (; ; ) {
    if (ch >= '0' && ch <= '9') {
      sp++;
    }
 else {
      break;
    }
    next();
  }
  boolean isDouble=false;
  if (ch == '.') {
    sp++;
    next();
    isDouble=true;
    for (; ; ) {
      if (ch >= '0' && ch <= '9') {
        sp++;
      }
 else {
        break;
      }
      next();
    }
  }
  if (ch == 'L') {
    sp++;
    next();
  }
 else   if (ch == 'S') {
    sp++;
    next();
  }
 else   if (ch == 'B') {
    sp++;
    next();
  }
 else   if (ch == 'F') {
    sp++;
    next();
    isDouble=true;
  }
 else   if (ch == 'D') {
    sp++;
    next();
    isDouble=true;
  }
 else   if (ch == 'e' || ch == 'E') {
    sp++;
    next();
    if (ch == '+' || ch == '-') {
      sp++;
      next();
    }
    for (; ; ) {
      if (ch >= '0' && ch <= '9') {
        sp++;
      }
 else {
        break;
      }
      next();
    }
    if (ch == 'D' || ch == 'F') {
      sp++;
      next();
    }
    isDouble=true;
  }
  if (isDouble) {
    token=JSONToken.LITERAL_FLOAT;
  }
 else {
    token=JSONToken.LITERAL_INT;
  }
}
