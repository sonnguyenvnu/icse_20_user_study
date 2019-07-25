@Override public void insert(List<String> items){
  YAMLValue yamlCompoundValue=argumentsKeyValue.getValue();
  if (!(yamlCompoundValue instanceof YAMLCompoundValue)) {
    return;
  }
  int appendEndOffset=-1;
  String insertString=null;
  if (yamlCompoundValue instanceof YAMLArrayImpl) {
    List<PsiElement> yamlArguments=YamlHelper.getYamlArrayOnSequenceOrArrayElements((YAMLCompoundValue)yamlCompoundValue);
    if (yamlArguments != null && yamlArguments.size() > 0) {
      appendEndOffset=yamlArguments.get(yamlArguments.size() - 1).getTextRange().getEndOffset();
      List<String> arrayList=new ArrayList<>();
      for (      String item : items) {
        arrayList.add(String.format("'@%s'",StringUtils.isNotBlank(item) ? item : "?"));
      }
      insertString=", " + StringUtils.join(arrayList,", ");
    }
  }
 else   if (yamlCompoundValue instanceof YAMLSequence) {
    String indent=StringUtil.repeatSymbol(' ',YAMLUtil.getIndentToThisElement(yamlCompoundValue));
    String eol=TranslationInsertUtil.findEol(yamlKeyValue);
    List<String> yamlSequences=new ArrayList<>();
    for (    String item : items) {
      yamlSequences.add(indent + String.format("- '@%s'",StringUtils.isNotBlank(item) ? item : "?"));
    }
    appendEndOffset=yamlCompoundValue.getTextRange().getEndOffset();
    insertString=eol + StringUtils.join(yamlSequences,eol);
  }
  if (appendEndOffset == -1) {
    return;
  }
  PsiDocumentManager manager=PsiDocumentManager.getInstance(project);
  Document document=manager.getDocument(yamlKeyValue.getContainingFile());
  if (document == null) {
    return;
  }
  document.insertString(appendEndOffset,insertString);
  manager.doPostponedOperationsAndUnblockDocument(document);
  manager.commitDocument(document);
}
