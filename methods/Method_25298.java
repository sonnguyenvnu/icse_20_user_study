private void checkOverlaps(Replacement replacement){
  Range<Integer> replacementRange=replacement.range();
  Collection<Replacement> overlap=overlaps.subRangeMap(replacementRange).asMapOfRanges().values();
  checkArgument(overlap.isEmpty(),"%s overlaps with existing replacements: %s",replacement,Joiner.on(", ").join(overlap));
  Set<Integer> containedZeroLengthRangeStarts=zeroLengthRanges.subSet(replacementRange.lowerEndpoint(),false,replacementRange.upperEndpoint(),false);
  checkArgument(containedZeroLengthRangeStarts.isEmpty(),"%s overlaps with existing zero-length replacements: %s",replacement,Joiner.on(", ").join(containedZeroLengthRangeStarts));
  overlaps.put(replacementRange,replacement);
  if (replacementRange.isEmpty()) {
    zeroLengthRanges.add(replacementRange.lowerEndpoint());
  }
}
