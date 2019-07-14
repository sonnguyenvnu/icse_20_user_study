private static SuggestedFix wrapInCodeTag(Range<Integer> containingPre){
  return SuggestedFix.builder().replace(containingPre.lowerEndpoint(),containingPre.lowerEndpoint(),"{@code ").replace(containingPre.upperEndpoint(),containingPre.upperEndpoint(),"}").build();
}
