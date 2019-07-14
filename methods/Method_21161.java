public void projectSearchResultClick(final @NonNull ProjectSearchResultViewHolder viewHolder,final @NonNull Project project){
  this.viewModel.inputs.projectClicked(project);
}
