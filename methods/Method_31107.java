public static void setPaddingEnd(@NonNull View view,int paddingEnd){
  ViewCompat.setPaddingRelative(view,ViewCompat.getPaddingStart(view),view.getPaddingTop(),paddingEnd,view.getPaddingBottom());
}
