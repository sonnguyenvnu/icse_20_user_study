private static ImmutableList<PropModel> getProps(ImmutableList<PropModel> rawProps,ImmutableList<String> cachedPropNames,ImmutableList<SpecMethodModel<DelegateMethod,Void>> delegateMethods){
  final List<DiffPropModel> diffPropModels=delegateMethods.stream().flatMap(f -> f.methodParams.stream()).filter(f -> f instanceof DiffPropModel).map(f -> (DiffPropModel)f).collect(Collectors.toList());
  final int basePropsSize=rawProps.size() - diffPropModels.size();
  final List<PropModel> baseProps=rawProps.subList(0,basePropsSize);
  final List<PropModel> renamedBaseProps=IntStream.range(0,baseProps.size()).mapToObj(i -> updatePropWithCachedName(baseProps.get(i),cachedPropNames,i)).collect(Collectors.toList());
  final SortedSet<PropModel> props=new TreeSet<>(MethodParamModelUtils.shallowParamComparator());
  props.addAll(renamedBaseProps);
  final List<PropModel> additionalProps=IntStream.range(baseProps.size(),baseProps.size() + diffPropModels.size()).mapToObj(i -> {
    final String cachedDiffPropName=i < cachedPropNames.size() ? cachedPropNames.get(i) : null;
    if (props.stream().noneMatch(prop -> diffPropModels.get(i - basePropsSize).isSameUnderlyingPropModel(prop,cachedDiffPropName))) {
      return updatePropWithCachedName(diffPropModels.get(i - basePropsSize).getUnderlyingPropModel(),cachedPropNames,i);
    }
    return null;
  }
).filter(Predicate.isEqual(null).negate()).collect(Collectors.toList());
  props.addAll(additionalProps);
  return ImmutableList.copyOf(new ArrayList<>(props));
}
