public void decreaseBlockDepth(){
  if (blockDepth == 0) {
    throw new FlywayException("Flyway parsing bug: unable to decrease block depth below 0");
  }
  blockDepth--;
}
