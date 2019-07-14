private void startIfAnimatable(Drawable drawable){
  if (drawable instanceof Animatable) {
    ((Animatable)drawable).start();
  }
}
