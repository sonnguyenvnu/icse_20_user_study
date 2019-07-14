public ActionBarMenuSubItem addSubItem(int id,int icon,CharSequence text){
  createPopupLayout();
  ActionBarMenuSubItem cell=new ActionBarMenuSubItem(getContext());
  cell.setTextAndIcon(text,icon);
  cell.setMinimumWidth(AndroidUtilities.dp(196));
  cell.setTag(id);
  popupLayout.addView(cell);
  LinearLayout.LayoutParams layoutParams=(LinearLayout.LayoutParams)cell.getLayoutParams();
  if (LocaleController.isRTL) {
    layoutParams.gravity=Gravity.RIGHT;
  }
  layoutParams.width=LayoutHelper.MATCH_PARENT;
  layoutParams.height=AndroidUtilities.dp(48);
  cell.setLayoutParams(layoutParams);
  cell.setOnClickListener(view -> {
    if (popupWindow != null && popupWindow.isShowing()) {
      if (processedPopupClick) {
        return;
      }
      processedPopupClick=true;
      popupWindow.dismiss(allowCloseAnimation);
    }
    if (parentMenu != null) {
      parentMenu.onItemClick((Integer)view.getTag());
    }
 else     if (delegate != null) {
      delegate.onItemClick((Integer)view.getTag());
    }
  }
);
  return cell;
}
