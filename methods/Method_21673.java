public static QueryAction createJoinAction(Client client,JoinSelect joinSelect){
  List<Condition> connectedConditions=joinSelect.getConnectedConditions();
  boolean allEqual=true;
  for (  Condition condition : connectedConditions) {
    if (condition.getOpear() != Condition.OPEAR.EQ) {
      allEqual=false;
      break;
    }
  }
  if (!allEqual)   return new ESNestedLoopsQueryAction(client,joinSelect);
  boolean useNestedLoopsHintExist=false;
  for (  Hint hint : joinSelect.getHints()) {
    if (hint.getType() == HintType.USE_NESTED_LOOPS) {
      useNestedLoopsHintExist=true;
      break;
    }
  }
  if (useNestedLoopsHintExist)   return new ESNestedLoopsQueryAction(client,joinSelect);
  return new ESHashJoinQueryAction(client,joinSelect);
}
