/** 
 * Given a list of integers computes a list of consecutive, monotonically non-descreasing ranges.
 */
public static @NonNull List<Range> consecutiveRanges(final @NonNull List<Integer> xs){
  final List<Range> result=new ArrayList<>();
  for (int idx=0; idx < xs.size(); idx++) {
    final Integer previous;
    final Integer current;
    if (idx == 0) {
      previous=null;
      current=xs.get(idx);
    }
 else {
      previous=xs.get(idx - 1);
      current=xs.get(idx);
    }
    if (previous == null) {
      result.add(Range.create(0,1));
    }
 else     if (current == previous + 1 || current.equals(previous)) {
      final int lastIdx=result.size() - 1;
      final Range lastRange=result.get(lastIdx);
      result.set(lastIdx,Range.create(lastRange.start,lastRange.length + 1));
    }
 else {
      result.add(Range.create(idx,1));
    }
  }
  return result;
}
