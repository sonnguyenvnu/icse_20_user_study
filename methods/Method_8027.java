public void setTextAndIcon(String text,int resId){
  try {
    textView.setText(text);
    Drawable drawable=getResources().getDrawable(resId).mutate();
    if (drawable != null) {
      drawable.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_chats_menuItemIcon),PorterDuff.Mode.MULTIPLY));
    }
    textView.setCompoundDrawablesWithIntrinsicBounds(drawable,null,null,null);
  }
 catch (  Throwable e) {
    FileLog.e(e);
  }
}
