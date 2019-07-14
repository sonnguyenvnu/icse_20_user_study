private RangeSet<Integer> fixGenerics(RangeSet<Integer> generics,RangeSet<Integer> preTags,RangeSet<Integer> dontEmitCodeFix,VisitorState state){
  RangeSet<Integer> emittedFixes=TreeRangeSet.create();
  for (  Range<Integer> range : generics.asRanges()) {
    if (emittedFixes.intersects(range) || dontEmitCodeFix.intersects(range)) {
      continue;
    }
    Range<Integer> regionToWrap=preTags.rangeContaining(range.lowerEndpoint());
    if (regionToWrap == null) {
      regionToWrap=range;
    }
    emittedFixes.add(regionToWrap);
    state.reportMatch(buildDescription(getDiagnosticPosition(range.lowerEndpoint(),state.getPath().getLeaf())).setMessage("This looks like a type with type parameters. The < and > characters here will " + "be interpreted as HTML, which can be avoided by wrapping it in a " + "{@code } tag.").addFix(wrapInCodeTag(regionToWrap)).build());
  }
  return emittedFixes;
}
