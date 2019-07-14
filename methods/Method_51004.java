private int mapToOffset(final int line,final int column){
  return lineToOffset.get(line) + column;
}
