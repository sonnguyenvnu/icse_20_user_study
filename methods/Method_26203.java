private static Comparator<MethodTree> comparingPositions(){
  return comparingInt(InconsistentOverloads::getStartPosition);
}
