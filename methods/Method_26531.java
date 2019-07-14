private Choice<List<BlockTemplateMatch>> matchesStartingAnywhere(JCBlock block,int offset,final ImmutableList<? extends StatementTree> statements,final Context context){
  Choice<List<BlockTemplateMatch>> choice=Choice.none();
  for (int i=0; i < statements.size(); i++) {
    choice=choice.or(matchesStartingAtBeginning(block,offset + i,statements.subList(i,statements.size()),context));
  }
  return choice.or(Choice.of(List.<BlockTemplateMatch>nil()));
}
