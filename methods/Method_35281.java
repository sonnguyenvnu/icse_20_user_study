@Override protected void onViewBound(@NonNull View view){
  super.onViewBound(view);
  if (displayUpMode != DisplayUpMode.SHOW) {
    view.findViewById(R.id.btn_up).setVisibility(View.GONE);
  }
  view.setBackgroundColor(ColorUtil.getMaterialColor(getResources(),index));
  tvTitle.setText(getResources().getString(R.string.navigation_title,index));
}
