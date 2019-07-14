@Override protected void onFragmentCreated(@NonNull View view,@Nullable Bundle savedInstanceState){
  if (savedInstanceState == null) {
    getPresenter().onFragmentCreated(getArguments());
  }
 else {
    if (userModel != null) {
      onInitViews(userModel);
    }
 else {
      getPresenter().onFragmentCreated(getArguments());
    }
  }
}
