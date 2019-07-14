public static ArrayList<IssueEventAdapterModel> addEvents(@Nullable List<IssueEvent> modelList){
  ArrayList<IssueEventAdapterModel> models=new ArrayList<>();
  if (modelList == null || modelList.isEmpty())   return models;
  Stream.of(modelList).forEach(issueEventModel -> models.add(new IssueEventAdapterModel(ROW,issueEventModel)));
  return models;
}
