public static @NonNull List<Range> positionalRanges(final @NonNull List<Integer> positions){
  final Integer firstPosition;
  if (positions.size() == 0) {
    return new ArrayList<>();
  }
  firstPosition=positions.get(0);
  final List<Range> ranges=RangeUtils.consecutiveRanges(positions);
  final List<Range> result=new ArrayList<>();
  for (  final Range range : ranges) {
    result.add(Range.create(range.start + firstPosition,range.length));
  }
  return result;
}
