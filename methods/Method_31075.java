public static void fadeToVisibility(@NonNull View view,boolean visible,boolean gone){
  if (visible) {
    fadeIn(view);
  }
 else {
    fadeOut(view,gone);
  }
}
