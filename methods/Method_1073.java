protected int getImageHash(@Nullable T image){
  return System.identityHashCode(image);
}
