@Override public void clearView(View view){
  if (Build.VERSION.SDK_INT >= 21) {
    final Object tag=view.getTag();
    if (tag instanceof Float) {
      ViewCompat.setElevation(view,(Float)tag);
    }
    view.setTag(null);
  }
  view.setTranslationX(0f);
  view.setTranslationY(0f);
}
