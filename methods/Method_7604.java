public TextView addSubItem(int id,CharSequence text){
  createPopupLayout();
  TextView textView=new TextView(getContext());
  textView.setTextColor(Theme.getColor(Theme.key_actionBarDefaultSubmenuItem));
  textView.setBackgroundDrawable(Theme.getSelectorDrawable(false));
  if (!LocaleController.isRTL) {
    textView.setGravity(Gravity.CENTER_VERTICAL);
  }
 else {
    textView.setGravity(Gravity.CENTER_VERTICAL | Gravity.RIGHT);
  }
  textView.setPadding(AndroidUtilities.dp(16),0,AndroidUtilities.dp(16),0);
  textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP,16);
  textView.setMinWidth(AndroidUtilities.dp(196));
  textView.setTag(id);
  textView.setText(text);
  popupLayout.addView(textView);
  LinearLayout.LayoutParams layoutParams=(LinearLayout.LayoutParams)textView.getLayoutParams();
  if (LocaleController.isRTL) {
    layoutParams.gravity=Gravity.RIGHT;
  }
  layoutParams.width=LayoutHelper.MATCH_PARENT;
  layoutParams.height=AndroidUtilities.dp(48);
  textView.setLayoutParams(layoutParams);
  textView.setOnClickListener(view -> {
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
  return textView;
}
