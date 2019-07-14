@RequiresApi(Build.VERSION_CODES.LOLLIPOP) @Override public void getOutline(@NonNull Outline outline){
  outline.setConvexPath(mPolygon);
}
