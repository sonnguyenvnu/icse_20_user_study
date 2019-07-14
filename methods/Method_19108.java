public static Map<Class<? extends Annotation>,DelegateMethodDescription> getGroupSectionSpecDelegatesMap(GroupSectionSpecModel specModel){
  Map<Class<? extends Annotation>,DelegateMethodDescription> groupSectionSpecDelegateMethodsMap=getTreeMap();
  groupSectionSpecDelegateMethodsMap.putAll(GROUP_SECTION_SPEC_DELEGATE_METHODS_MAP);
  addServiceAwareDelegateMethodDescriptions(groupSectionSpecDelegateMethodsMap,specModel);
  return Collections.unmodifiableMap(groupSectionSpecDelegateMethodsMap);
}
