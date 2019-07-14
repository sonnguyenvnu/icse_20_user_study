@Override protected void onBoundsChange(@NonNull Rect bounds){
  if (mDrawable != null) {
    mDrawable.setBounds(bounds);
  }
}
