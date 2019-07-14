public void usageMethod(String className,String name,String desc,MemberNode usingMemberNode){
  checkClassName(className);
  if (classFilter.filter(className)) {
    MemberNode memberNode;
    if (ClassLoaderUtil.CLINIT.equals(name) || ClassLoaderUtil.INIT.equals(name)) {
      memberNode=defineConstructor(className,name,desc);
    }
 else {
      memberNode=defineMethod(className,name,desc);
    }
    usage(memberNode,usingMemberNode);
  }
}
