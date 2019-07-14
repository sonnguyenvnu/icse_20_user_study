private String findIdForViewModel(final @NonNull ActivityViewModel activityViewModel){
  for (  final Map.Entry<String,ActivityViewModel> entry : this.viewModels.entrySet()) {
    if (activityViewModel.equals(entry.getValue())) {
      return entry.getKey();
    }
  }
  throw new RuntimeException("Cannot find view model in map!");
}
