private void showMenuForEntity(final EntityView entityView){
  int x=(int)((entityView.getPosition().x - entitiesView.getWidth() / 2) * entitiesView.getScaleX());
  int y=(int)((entityView.getPosition().y - entityView.getHeight() * entityView.getScale() / 2 - entitiesView.getHeight() / 2) * entitiesView.getScaleY()) - AndroidUtilities.dp(32);
  showPopup(() -> {
    LinearLayout parent=new LinearLayout(getContext());
    parent.setOrientation(LinearLayout.HORIZONTAL);
    TextView deleteView=new TextView(getContext());
    deleteView.setTextColor(Theme.getColor(Theme.key_actionBarDefaultSubmenuItem));
    deleteView.setBackgroundDrawable(Theme.getSelectorDrawable(false));
    deleteView.setGravity(Gravity.CENTER_VERTICAL);
    deleteView.setPadding(AndroidUtilities.dp(16),0,AndroidUtilities.dp(14),0);
    deleteView.setTextSize(TypedValue.COMPLEX_UNIT_DIP,18);
    deleteView.setTag(0);
    deleteView.setText(LocaleController.getString("PaintDelete",R.string.PaintDelete));
    deleteView.setOnClickListener(v -> {
      removeEntity(entityView);
      if (popupWindow != null && popupWindow.isShowing()) {
        popupWindow.dismiss(true);
      }
    }
);
    parent.addView(deleteView,LayoutHelper.createLinear(LayoutHelper.WRAP_CONTENT,48));
    if (entityView instanceof TextPaintView) {
      TextView editView=new TextView(getContext());
      editView.setTextColor(Theme.getColor(Theme.key_actionBarDefaultSubmenuItem));
      editView.setBackgroundDrawable(Theme.getSelectorDrawable(false));
      editView.setGravity(Gravity.CENTER_VERTICAL);
      editView.setPadding(AndroidUtilities.dp(16),0,AndroidUtilities.dp(16),0);
      editView.setTextSize(TypedValue.COMPLEX_UNIT_DIP,18);
      editView.setTag(1);
      editView.setText(LocaleController.getString("PaintEdit",R.string.PaintEdit));
      editView.setOnClickListener(v -> {
        editSelectedTextEntity();
        if (popupWindow != null && popupWindow.isShowing()) {
          popupWindow.dismiss(true);
        }
      }
);
      parent.addView(editView,LayoutHelper.createLinear(LayoutHelper.WRAP_CONTENT,48));
    }
    TextView duplicateView=new TextView(getContext());
    duplicateView.setTextColor(Theme.getColor(Theme.key_actionBarDefaultSubmenuItem));
    duplicateView.setBackgroundDrawable(Theme.getSelectorDrawable(false));
    duplicateView.setGravity(Gravity.CENTER_VERTICAL);
    duplicateView.setPadding(AndroidUtilities.dp(14),0,AndroidUtilities.dp(16),0);
    duplicateView.setTextSize(TypedValue.COMPLEX_UNIT_DIP,18);
    duplicateView.setTag(2);
    duplicateView.setText(LocaleController.getString("PaintDuplicate",R.string.PaintDuplicate));
    duplicateView.setOnClickListener(v -> {
      duplicateSelectedEntity();
      if (popupWindow != null && popupWindow.isShowing()) {
        popupWindow.dismiss(true);
      }
    }
);
    parent.addView(duplicateView,LayoutHelper.createLinear(LayoutHelper.WRAP_CONTENT,48));
    popupLayout.addView(parent);
    LinearLayout.LayoutParams params=(LinearLayout.LayoutParams)parent.getLayoutParams();
    params.width=LayoutHelper.WRAP_CONTENT;
    params.height=LayoutHelper.WRAP_CONTENT;
    parent.setLayoutParams(params);
  }
,entityView,Gravity.CENTER,x,y);
}
