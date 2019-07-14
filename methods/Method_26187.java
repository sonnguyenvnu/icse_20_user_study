private Description makeFix(VisitorState state,Tree declaration,Tree matchedTree,String message){
  return buildDescription(matchedTree).setMessage(message).addFix(NullnessFixes.makeFix(state,declaration)).build();
}
