@Override public int next(){
  if (availableCodes.isEmpty())   generate();
  return availableCodes.poll();
}
