public static void setPaddingStart(@NonNull View view,int paddingStart){
  ViewCompat.setPaddingRelative(view,paddingStart,view.getPaddingTop(),ViewCompat.getPaddingEnd(view),view.getPaddingBottom());
}
