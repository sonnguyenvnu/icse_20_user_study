/** 
 * Returns a  {@link Fix} that adds members defined by {@code firstMember} (and optionally {@code otherMembers}) to the end of the class referenced by  {@code classTree}. This method should only be called once per  {@link ClassTree} as the suggestions will otherwise collide.
 */
public static Fix addMembers(ClassTree classTree,VisitorState state,String firstMember,String... otherMembers){
  checkNotNull(classTree);
  int classEndPosition=state.getEndPosition(classTree);
  StringBuilder stringBuilder=new StringBuilder();
  for (  String memberSnippet : Lists.asList(firstMember,otherMembers)) {
    stringBuilder.append("\n\n").append(memberSnippet);
  }
  stringBuilder.append('\n');
  return SuggestedFix.replace(classEndPosition - 1,classEndPosition - 1,stringBuilder.toString());
}
