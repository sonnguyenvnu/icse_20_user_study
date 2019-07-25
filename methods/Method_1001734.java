private boolean eat(int charToEat){
  while (ch == ' ') {
    nextChar();
  }
  if (ch == charToEat) {
    nextChar();
    return true;
  }
  return false;
}
