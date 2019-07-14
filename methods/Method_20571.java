private void assignViewModel(final @Nullable Bundle viewModelEnvelope){
  if (this.viewModel == null) {
    final RequiresActivityViewModel annotation=getClass().getAnnotation(RequiresActivityViewModel.class);
    final Class<ViewModelType> viewModelClass=annotation == null ? null : (Class<ViewModelType>)annotation.value();
    if (viewModelClass != null) {
      this.viewModel=ActivityViewModelManager.getInstance().fetch(this,viewModelClass,BundleUtils.maybeGetBundle(viewModelEnvelope,VIEW_MODEL_KEY));
    }
  }
}
