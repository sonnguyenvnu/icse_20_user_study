@SuppressWarnings("unchecked") public <T extends FragmentViewModel>T fetch(final @NonNull Context context,final @NonNull Class<T> viewModelClass,final @Nullable Bundle savedInstanceState){
  final String id=fetchId(savedInstanceState);
  FragmentViewModel viewModel=this.viewModels.get(id);
  if (viewModel == null) {
    viewModel=create(context,viewModelClass,savedInstanceState,id);
  }
  return (T)viewModel;
}
