@NonNull @SuppressWarnings("WeakerAccess") public static Rect getLayoutPosition(@NonNull View view){
  Rect myViewRect=new Rect();
  view.getGlobalVisibleRect(myViewRect);
  return myViewRect;
}
