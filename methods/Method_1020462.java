static RtaResult analyse(List<TypeInfo> typeInfos){
  List<Type> types=TypeGraphBuilder.build(typeInfos);
  types.stream().flatMap(t -> t.getMembers().stream()).filter(Member::isJsAccessible).forEach(m -> onMemberReference(m.getDefaultInvocationKind(),m));
  return RtaResult.build(getUnusedTypes(types),getUnusedMembers(types));
}
