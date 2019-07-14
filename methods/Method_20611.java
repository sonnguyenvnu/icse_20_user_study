private String findIdForViewModel(final @NonNull FragmentViewModel viewModel){
  for (  final Map.Entry<String,FragmentViewModel> entry : this.viewModels.entrySet()) {
    if (viewModel.equals(entry.getValue())) {
      return entry.getKey();
    }
  }
  throw new RuntimeException("Cannot find view model in map!");
}
