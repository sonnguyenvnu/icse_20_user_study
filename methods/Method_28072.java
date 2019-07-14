@Override public void onSubmitLabel(@NonNull String name,@NonNull String color,@NonNull String repo,@NonNull String login){
  LabelModel labelModel=new LabelModel();
  labelModel.setColor(color.replaceAll("#",""));
  labelModel.setName(name);
  makeRestCall(RestProvider.getRepoService(isEnterprise()).addLabel(login,repo,labelModel),labelModel1 -> sendToView(view -> view.onSuccessfullyCreated(labelModel1)));
}
