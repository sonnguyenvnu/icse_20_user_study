private void createBackButtonImage(){
  if (backButtonImageView != null) {
    return;
  }
  backButtonImageView=new ImageView(getContext());
  backButtonImageView.setScaleType(ImageView.ScaleType.CENTER);
  backButtonImageView.setBackgroundDrawable(Theme.createSelectorDrawable(itemsBackgroundColor));
  if (itemsColor != 0) {
    backButtonImageView.setColorFilter(new PorterDuffColorFilter(itemsColor,PorterDuff.Mode.MULTIPLY));
  }
  backButtonImageView.setPadding(AndroidUtilities.dp(1),0,0,0);
  addView(backButtonImageView,LayoutHelper.createFrame(54,54,Gravity.LEFT | Gravity.TOP));
  backButtonImageView.setOnClickListener(v -> {
    if (!actionModeVisible && isSearchFieldVisible) {
      closeSearchField();
      return;
    }
    if (actionBarMenuOnItemClick != null) {
      actionBarMenuOnItemClick.onItemClick(-1);
    }
  }
);
  backButtonImageView.setContentDescription(LocaleController.getString("AccDescrGoBack",R.string.AccDescrGoBack));
}
