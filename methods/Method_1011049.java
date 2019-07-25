public void execute(SNode node){
  if ((boolean)Expression__BehaviorDescriptor.singleValue_id1o8Ht9sES3u.invoke(SNodeOperations.asSConcept(SNodeOperations.getConcept(SLinkOperations.getTarget(((SNode)RemoveUnnecessaryParentheses_QuickFix.this.getField("bottomLineParens")[0]),MetaAdapterFactory.getContainmentLink(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0xfb4ed32b7fL,0xfb4ed32b80L,"expression")))))) {
    SNodeOperations.replaceWithAnother(node,SLinkOperations.getTarget(((SNode)RemoveUnnecessaryParentheses_QuickFix.this.getField("bottomLineParens")[0]),MetaAdapterFactory.getContainmentLink(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0xfb4ed32b7fL,0xfb4ed32b80L,"expression")));
  }
  SNodeOperations.replaceWithAnother(node,((SNode)RemoveUnnecessaryParentheses_QuickFix.this.getField("bottomLineParens")[0]));
}
