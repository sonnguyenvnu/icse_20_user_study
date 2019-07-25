private void reset(){
  mRadiusImageView.setBorderColor(ContextCompat.getColor(getContext(),R.color.radiusImageView_border_color));
  mRadiusImageView.setBorderWidth(DensityUtils.dp2px(getContext(),2));
  mRadiusImageView.setCornerRadius(DensityUtils.dp2px(getContext(),10));
  mRadiusImageView.setSelectedMaskColor(ContextCompat.getColor(getContext(),R.color.radiusImageView_selected_mask_color));
  mRadiusImageView.setSelectedBorderColor(ContextCompat.getColor(getContext(),R.color.radiusImageView_selected_border_color));
  mRadiusImageView.setSelectedBorderWidth(DensityUtils.dp2px(getContext(),3));
  mRadiusImageView.setTouchSelectModeEnabled(true);
  mRadiusImageView.setCircle(false);
  mRadiusImageView.setOval(false);
}
