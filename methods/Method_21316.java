@Override public void onBind(){
  final Context context=context();
  this.filterTextView.setText(this.item.params().filterString(context,environment().ksString()));
  final int textColor=this.item.selected() ? this.accentColor : this.ksrSoftBlackColor;
  this.filterTextView.setTextColor(textColor);
  final Drawable iconDrawable=this.item.selected() ? this.labelSelectedDrawable : this.labelUnselectedDrawable;
  this.filterTextView.setCompoundDrawablesRelativeWithIntrinsicBounds(iconDrawable,null,null,null);
  final Drawable backgroundDrawable=this.item.selected() ? this.selectedBackgroundDrawable : null;
  this.filterTextView.setBackground(backgroundDrawable);
}
