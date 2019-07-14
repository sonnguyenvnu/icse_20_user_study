@Override protected void onViewBound(@NonNull View view){
  super.onViewBound(view);
  tvTitle.setText(getArgs().getString(KEY_TITLE));
  int bgColor=getArgs().getInt(KEY_BG_COLOR);
  if (getArgs().getBoolean(KEY_COLOR_IS_RES)) {
    bgColor=ContextCompat.getColor(getActivity(),bgColor);
  }
  view.setBackgroundColor(bgColor);
}
