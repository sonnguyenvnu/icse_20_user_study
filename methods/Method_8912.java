private void showBrushSettings(){
  showPopup(() -> {
    View radial=buttonForBrush(0,R.drawable.paint_radial_preview,currentBrush == 0);
    popupLayout.addView(radial);
    LinearLayout.LayoutParams layoutParams=(LinearLayout.LayoutParams)radial.getLayoutParams();
    layoutParams.width=LayoutHelper.MATCH_PARENT;
    layoutParams.height=AndroidUtilities.dp(52);
    radial.setLayoutParams(layoutParams);
    View elliptical=buttonForBrush(1,R.drawable.paint_elliptical_preview,currentBrush == 1);
    popupLayout.addView(elliptical);
    layoutParams=(LinearLayout.LayoutParams)elliptical.getLayoutParams();
    layoutParams.width=LayoutHelper.MATCH_PARENT;
    layoutParams.height=AndroidUtilities.dp(52);
    elliptical.setLayoutParams(layoutParams);
    View neon=buttonForBrush(2,R.drawable.paint_neon_preview,currentBrush == 2);
    popupLayout.addView(neon);
    layoutParams=(LinearLayout.LayoutParams)neon.getLayoutParams();
    layoutParams.width=LayoutHelper.MATCH_PARENT;
    layoutParams.height=AndroidUtilities.dp(52);
    neon.setLayoutParams(layoutParams);
  }
,this,Gravity.RIGHT | Gravity.BOTTOM,0,AndroidUtilities.dp(48));
}
