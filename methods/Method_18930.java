private static ImmutableList<RenderDataDiffModel> getDiffs(ImmutableList<SpecMethodModel<DelegateMethod,Void>> delegateMethods){
  final Set<RenderDataDiffModel> diffs=new TreeSet<>(MethodParamModelUtils.shallowParamComparator());
  for (  SpecMethodModel<DelegateMethod,Void> delegateMethod : delegateMethods) {
    for (    MethodParamModel param : delegateMethod.methodParams) {
      if (param instanceof RenderDataDiffModel) {
        diffs.add((RenderDataDiffModel)param);
      }
    }
  }
  return ImmutableList.copyOf(new ArrayList<>(diffs));
}
