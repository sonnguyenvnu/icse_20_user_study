private FrameLayout buttonForBrush(final int brush,int resource,boolean selected){
  FrameLayout button=new FrameLayout(getContext());
  button.setBackgroundDrawable(Theme.getSelectorDrawable(false));
  button.setOnClickListener(v -> {
    setBrush(brush);
    if (popupWindow != null && popupWindow.isShowing()) {
      popupWindow.dismiss(true);
    }
  }
);
  ImageView preview=new ImageView(getContext());
  preview.setImageResource(resource);
  button.addView(preview,LayoutHelper.createFrame(165,44,Gravity.LEFT | Gravity.CENTER_VERTICAL,46,0,8,0));
  if (selected) {
    ImageView check=new ImageView(getContext());
    check.setImageResource(R.drawable.ic_ab_done);
    check.setScaleType(ImageView.ScaleType.CENTER);
    button.addView(check,LayoutHelper.createFrame(50,LayoutHelper.MATCH_PARENT));
  }
  return button;
}
