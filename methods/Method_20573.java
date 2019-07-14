private void assignViewModel(final @Nullable Bundle viewModelEnvelope){
  if (this.viewModel == null) {
    final RequiresFragmentViewModel annotation=getClass().getAnnotation(RequiresFragmentViewModel.class);
    final Class<ViewModelType> viewModelClass=annotation == null ? null : (Class<ViewModelType>)annotation.value();
    if (viewModelClass != null) {
      this.viewModel=FragmentViewModelManager.getInstance().fetch(getContext(),viewModelClass,BundleUtils.maybeGetBundle(viewModelEnvelope,VIEW_MODEL_KEY));
    }
  }
}
