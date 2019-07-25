@Override public void insert(List<String> items){
  PsiDocumentManager manager=PsiDocumentManager.getInstance(serviceKeyValue.getProject());
  Document document=manager.getDocument(serviceKeyValue.getContainingFile());
  if (document == null) {
    return;
  }
  List<String> arrayList=new ArrayList<>();
  for (  String item : items) {
    arrayList.add("'@" + (StringUtils.isNotBlank(item) ? item : "?") + "'");
  }
  YamlHelper.putKeyValue(serviceKeyValue,"arguments","[" + StringUtils.join(arrayList,", ") + "]");
  manager.doPostponedOperationsAndUnblockDocument(document);
  manager.commitDocument(document);
}
