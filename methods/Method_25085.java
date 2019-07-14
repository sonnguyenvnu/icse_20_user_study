private void addLabel(FloatingActionButton fab){
  String text=fab.getLabelText();
  if (TextUtils.isEmpty(text))   return;
  final Label label=new Label(mLabelsContext);
  label.setClickable(true);
  label.setFab(fab);
  label.setShowAnimation(AnimationUtils.loadAnimation(getContext(),mLabelsShowAnimation));
  label.setHideAnimation(AnimationUtils.loadAnimation(getContext(),mLabelsHideAnimation));
  if (mLabelsStyle > 0) {
    label.setTextAppearance(getContext(),mLabelsStyle);
    label.setShowShadow(false);
    label.setUsingStyle(true);
  }
 else {
    label.setColors(mLabelsColorNormal,mLabelsColorPressed,mLabelsColorRipple);
    label.setShowShadow(mLabelsShowShadow);
    label.setCornerRadius(mLabelsCornerRadius);
    if (mLabelsEllipsize > 0) {
      setLabelEllipsize(label);
    }
    label.setMaxLines(mLabelsMaxLines);
    label.updateBackground();
    label.setTextSize(TypedValue.COMPLEX_UNIT_PX,mLabelsTextSize);
    label.setTextColor(mLabelsTextColor);
    int left=mLabelsPaddingLeft;
    int top=mLabelsPaddingTop;
    if (mLabelsShowShadow) {
      left+=fab.getShadowRadius() + Math.abs(fab.getShadowXOffset());
      top+=fab.getShadowRadius() + Math.abs(fab.getShadowYOffset());
    }
    label.setPadding(left,top,mLabelsPaddingLeft,mLabelsPaddingTop);
    if (mLabelsMaxLines < 0 || mLabelsSingleLine) {
      label.setSingleLine(mLabelsSingleLine);
    }
  }
  if (mCustomTypefaceFromFont != null) {
    label.setTypeface(mCustomTypefaceFromFont);
  }
  label.setText(text);
  label.setOnClickListener(fab.getOnClickListener());
  addView(label);
  fab.setTag(R.id.fab_label,label);
}
