private boolean eat(char charToEat){
  while (ch == ' ') {
    nextChar();
  }
  if (ch == charToEat) {
    nextChar();
    return true;
  }
  return false;
}
