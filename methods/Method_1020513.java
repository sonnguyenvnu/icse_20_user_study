/** 
 * Gather information from a Type and create a TypeInfo object used to build the call graph. 
 */
public static TypeInfo build(Type type,String headerFilePath,String implFilePath,Map<Member,SourcePosition> outputSourceInfoByMember){
  String typeId=getTypeId(type);
  TypeInfo.Builder typeInfoBuilder=TypeInfo.newBuilder().setTypeId(typeId).setHeaderSourceFilePath(headerFilePath).setImplSourceFilePath(implFilePath);
  DeclaredTypeDescriptor superTypeDescriptor=type.getSuperTypeDescriptor();
  if (superTypeDescriptor != null && !superTypeDescriptor.getTypeDeclaration().isStarOrUnknown()) {
    typeInfoBuilder.setExtendsType(getTypeId(superTypeDescriptor));
  }
  for (  DeclaredTypeDescriptor superInterfaceType : type.getSuperInterfaceTypeDescriptors()) {
    typeInfoBuilder.addImplementsType(getTypeId(superInterfaceType));
  }
  Map<String,MemberInfo.Builder> memberInfoBuilderByName=new LinkedHashMap<>(type.getMembers().size());
  boolean forceJsAccessible=isAccesssedFromJ2clBootstrapJsFiles(type.getTypeDescriptor());
  for (  Member member : type.getMembers()) {
    MemberDescriptor memberDescriptor=member.getDescriptor();
    String memberName=getMemberId(memberDescriptor);
    boolean isJsAccessible=((memberDescriptor.isJsFunction() || memberDescriptor.isJsMember()) && !shouldNotBeJsAccessible(memberDescriptor)) || forceJsAccessible;
    MemberInfo.Builder builder=memberInfoBuilderByName.computeIfAbsent(memberName,m -> MemberInfo.newBuilder().setName(memberName).setPublic(memberDescriptor.getVisibility().isPublic()).setStatic(member.isStatic()).setJsAccessible(isJsAccessible));
    SourcePosition jsSourcePosition=outputSourceInfoByMember.get(member);
    if (jsSourcePosition != null) {
      builder.setStartPosition(createFilePosition(jsSourcePosition.getStartFilePosition()));
      builder.setEndPosition(createFilePosition(jsSourcePosition.getEndFilePosition()));
    }
    collectReferencedTypesAndMethodInvocations(member,builder);
  }
  return typeInfoBuilder.addAllMember(memberInfoBuilderByName.values().stream().map(MemberInfo.Builder::build).collect(Collectors.toList())).build();
}
