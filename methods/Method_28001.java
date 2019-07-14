@Override protected void onFragmentCreated(@NonNull View view,@Nullable Bundle savedInstanceState){
  adapter=new RepoFilePathsAdapter(getPresenter().getPaths());
  adapter.setListener(getPresenter());
  recycler.setAdapter(adapter);
  branches.setText(ref);
  if (savedInstanceState == null) {
    getPresenter().onFragmentCreated(getArguments());
  }
 else   if (getPresenter().getPaths().isEmpty() && !getPresenter().isApiCalled()) {
    getPresenter().onFragmentCreated(getArguments());
  }
  branches.setText(getPresenter().getDefaultBranch());
  if (Login.getUser().getLogin().equalsIgnoreCase(getPresenter().login) || (repoCallback != null && repoCallback.isCollaborator())) {
    addFile.setVisibility(View.VISIBLE);
  }
}
