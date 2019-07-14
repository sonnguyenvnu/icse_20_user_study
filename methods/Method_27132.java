public static ArrayList<PullRequestAdapterModel> addEvents(@Nullable List<IssueEvent> modelList){
  ArrayList<PullRequestAdapterModel> models=new ArrayList<>();
  if (modelList == null || modelList.isEmpty())   return models;
  Stream.of(modelList).forEach(issueEventModel -> models.add(new PullRequestAdapterModel(ROW,issueEventModel)));
  return models;
}
