public synchronized void setType(int type){
  if (type != YES_NO_HABIT && type != NUMBER_HABIT)   throw new IllegalArgumentException();
  data.type=type;
}
