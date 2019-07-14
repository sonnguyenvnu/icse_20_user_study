private void setupView(@NonNull Context context){
  setBackground(ContextCompat.getDrawable(context,R.drawable.ripple));
  int verticalPadding=getResources().getDimensionPixelOffset(R.dimen.developer_small_Spacing);
  setPadding(0,0,0,verticalPadding);
}
