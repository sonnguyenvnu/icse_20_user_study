public void setItemsBackgroundColor(int color,boolean isActionMode){
  if (isActionMode) {
    itemsActionModeBackgroundColor=color;
    if (actionModeVisible) {
      if (backButtonImageView != null) {
        backButtonImageView.setBackgroundDrawable(Theme.createSelectorDrawable(itemsActionModeBackgroundColor));
      }
    }
    if (actionMode != null) {
      actionMode.updateItemsBackgroundColor();
    }
  }
 else {
    itemsBackgroundColor=color;
    if (backButtonImageView != null) {
      backButtonImageView.setBackgroundDrawable(Theme.createSelectorDrawable(itemsBackgroundColor));
    }
    if (menu != null) {
      menu.updateItemsBackgroundColor();
    }
  }
}
