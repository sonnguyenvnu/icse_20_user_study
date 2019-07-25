private List<String> removals(List<Integer> codePoints){
  return stream(halving(codePoints.size()).spliterator(),false).map(i -> Lists.removeFrom(codePoints,i)).flatMap(Collection::stream).map(this::convert).collect(toList());
}
