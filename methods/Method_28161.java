@Override public void onPutLabels(@NonNull ArrayList<LabelModel> labels){
  makeRestCall(RestProvider.getIssueService(isEnterprise()).putLabels(login,repoId,issueNumber,Stream.of(labels).filter(value -> value != null && value.getName() != null).map(LabelModel::getName).collect(Collectors.toList())),labelModels -> {
    sendToView(view -> updateTimeline(view,R.string.labels_added_successfully));
    LabelListModel listModel=new LabelListModel();
    listModel.addAll(labels);
    issueModel.setLabels(listModel);
    manageObservable(issueModel.save(issueModel).toObservable());
  }
);
}
