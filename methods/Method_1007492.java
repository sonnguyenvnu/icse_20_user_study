void claim(boolean test){
  ndone++;
  if (!test)   throw new RuntimeException();
}
