@Override public void apply(View view){
  if (isDrawableType()) {
    if (view instanceof SeekBar) {
      ((SeekBar)view).setThumb(SkinManager.getInstance().getDrawable(getResId()));
    }
  }
}
