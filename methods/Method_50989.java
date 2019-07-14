public void usageField(String className,String name,String desc,MemberNode usingMemberNode){
  checkClassName(className);
  if (classFilter.filter(className)) {
    FieldNode fieldNode=defineField(className,name,desc);
    usage(fieldNode,usingMemberNode);
  }
}
