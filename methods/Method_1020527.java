public void bind(boolean isSelected,boolean isShowDivider){
  platformIcon.loadPlatformIcon(mItem);
  platformName.setText(mItem.getName());
  radioBtn.setChecked(isSelected);
  divider.setVisibility(isShowDivider ? View.VISIBLE : View.GONE);
}
