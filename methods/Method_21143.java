/** 
 * Sets an OffsetChangedListener for the view's AppBarLayout to: 1. determine the toolbar's alpha based on scroll range 2. adjust the view's bottom padding via inputs
 */
private void setAppBarOffsetChangedListener(final @NonNull AppBarLayout appBarLayout){
  appBarLayout.addOnOffsetChangedListener((layout,offset) -> {
    this.projectNameToolbarTextView.setAlpha(Math.abs(offset) / layout.getTotalScrollRange());
    this.viewModel.inputs.appBarTotalScrollRange(layout.getTotalScrollRange());
    this.viewModel.inputs.appBarOffset(offset);
  }
);
}
