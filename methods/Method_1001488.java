public static Animation singleton(AffineTransformation affineTransformation){
  if (affineTransformation == null) {
    return null;
  }
  return new Animation(Collections.singletonList(affineTransformation));
}
