public void addSubItem(int id,View view,int width,int height){
  createPopupLayout();
  view.setLayoutParams(new LinearLayout.LayoutParams(width,height));
  popupLayout.addView(view);
  view.setTag(id);
  view.setOnClickListener(view1 -> {
    if (popupWindow != null && popupWindow.isShowing()) {
      if (processedPopupClick) {
        return;
      }
      processedPopupClick=true;
      popupWindow.dismiss(allowCloseAnimation);
    }
    if (parentMenu != null) {
      parentMenu.onItemClick((Integer)view1.getTag());
    }
 else     if (delegate != null) {
      delegate.onItemClick((Integer)view1.getTag());
    }
  }
);
  view.setBackgroundDrawable(Theme.getSelectorDrawable(false));
}
