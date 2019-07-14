private static SuggestedFix.Builder addPrivateConstructor(ClassTree classTree){
  SuggestedFix.Builder fix=SuggestedFix.builder();
  String indent="  ";
  for (  Tree member : classTree.getMembers()) {
    if (member.getKind().equals(METHOD) && !isGeneratedConstructor((MethodTree)member)) {
      fix.prefixWith(member,indent + "private " + classTree.getSimpleName() + "() {} // no instances\n" + indent);
      break;
    }
    if (!member.getKind().equals(METHOD)) {
      indent="";
    }
  }
  return fix;
}
