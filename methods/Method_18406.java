private static void unsetClipChildren(View view,boolean clipChildren){
  if (!clipChildren && view instanceof ViewGroup) {
    ((ViewGroup)view).setClipChildren(true);
  }
}
