@Override protected void onViewIsDetaching(){
  DrawableGetter drawableGetter=(DrawableGetter)comment.getTag(R.id.drawable_callback);
  if (drawableGetter != null) {
    drawableGetter.clear(drawableGetter);
  }
}
