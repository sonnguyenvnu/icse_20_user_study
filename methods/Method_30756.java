private void init(){
  Context context=getContext();
  Drawable background=AppCompatResources.getDrawable(context,R.drawable.colored_border_button_background);
  if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
    background=DrawableCompat.wrap(background);
    DrawableCompat.setTint(background,ViewUtils.getColorFromAttrRes(R.attr.colorAccent,0,context));
  }
  ViewCompat.setBackground(this,background);
}
