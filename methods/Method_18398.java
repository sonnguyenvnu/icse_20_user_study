private static void unsetInterceptTouchEventHandler(View view){
  if (view instanceof ComponentHost) {
    ((ComponentHost)view).setInterceptTouchEventHandler(null);
  }
}
