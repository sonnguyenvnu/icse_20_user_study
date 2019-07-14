static CodeBlock generateApplyingLazyStateUpdateCode(int index,StateParamModel stateValue){
  final int stateUpdateType=FLAG_LAZY | index;
  final String stateName=stateValue.getName();
  final TypeName stateType=stateValue.getTypeName();
  return CodeBlock.builder().add("case $L:\n$>",stateUpdateType).addStatement("this.$L = ($T) $L[0]",stateName,stateType,VAR_NAME_PARAMS).addStatement("break$<").build();
}
