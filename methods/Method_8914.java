private void showTextSettings(){
  showPopup(() -> {
    View outline=buttonForText(true,LocaleController.getString("PaintOutlined",R.string.PaintOutlined),selectedStroke);
    popupLayout.addView(outline);
    LinearLayout.LayoutParams layoutParams=(LinearLayout.LayoutParams)outline.getLayoutParams();
    layoutParams.width=LayoutHelper.MATCH_PARENT;
    layoutParams.height=AndroidUtilities.dp(48);
    outline.setLayoutParams(layoutParams);
    View regular=buttonForText(false,LocaleController.getString("PaintRegular",R.string.PaintRegular),!selectedStroke);
    popupLayout.addView(regular);
    layoutParams=(LinearLayout.LayoutParams)regular.getLayoutParams();
    layoutParams.width=LayoutHelper.MATCH_PARENT;
    layoutParams.height=AndroidUtilities.dp(48);
    regular.setLayoutParams(layoutParams);
  }
,this,Gravity.RIGHT | Gravity.BOTTOM,0,AndroidUtilities.dp(48));
}
