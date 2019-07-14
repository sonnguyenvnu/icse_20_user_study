protected String getImageClass(@Nullable T image){
  return (image != null) ? image.getClass().getSimpleName() : "<null>";
}
