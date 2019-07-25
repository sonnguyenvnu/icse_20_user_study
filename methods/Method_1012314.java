@SuppressLint("ShowToast") @CheckResult public static Toast custom(@NonNull Context context,@NonNull CharSequence message,Drawable icon,@ColorInt int tintColor,@ColorInt int textColor,int duration,boolean withIcon,boolean shouldTint){
  final Toast currentToast=Toast.makeText(context,"",duration);
  final View toastLayout=((LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.xtoast_layout,null);
  final ImageView toastIcon=toastLayout.findViewById(R.id.toast_icon);
  final TextView toastTextView=toastLayout.findViewById(R.id.toast_text);
  Drawable drawableFrame;
  if (shouldTint) {
    drawableFrame=Utils.tint9PatchDrawableFrame(context,tintColor);
  }
 else {
    drawableFrame=Utils.getDrawable(context,R.drawable.xtoast_frame);
  }
  Utils.setBackground(toastLayout,drawableFrame);
  if (withIcon) {
    if (icon == null) {
      throw new IllegalArgumentException("Avoid passing 'icon' as null if 'withIcon' is set to true");
    }
    Utils.setBackground(toastIcon,Config.get().tintIcon ? Utils.tintIcon(icon,textColor) : icon);
  }
 else {
    toastIcon.setVisibility(View.GONE);
  }
  toastTextView.setText(message);
  toastTextView.setTextColor(textColor);
  if (XUI.getDefaultTypeface() == null) {
    toastTextView.setTypeface(Config.get().typeface);
  }
  if (Config.get().textSize != -1) {
    toastTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP,Config.get().textSize);
  }
  if (Config.get().alpha != -1) {
    toastLayout.getBackground().setAlpha(Config.get().alpha);
  }
  currentToast.setView(toastLayout);
  if (!Config.get().allowQueue) {
    if (lastToast != null) {
      lastToast.cancel();
    }
    lastToast=currentToast;
  }
  if (Config.get().gravity != -1) {
    currentToast.setGravity(Config.get().gravity,Config.get().xOffset,Config.get().yOffset);
  }
  return currentToast;
}
