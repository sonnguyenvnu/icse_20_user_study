private static Property getViewAnimatorProperty(AnimatedProperty animatedProperty){
  if (animatedProperty == AnimatedProperties.ALPHA) {
    return View.ALPHA;
  }
  if (animatedProperty == AnimatedProperties.X) {
    return View.X;
  }
  if (animatedProperty == AnimatedProperties.Y) {
    return View.Y;
  }
  if (animatedProperty == AnimatedProperties.ROTATION) {
    return View.ROTATION;
  }
  throw new IllegalArgumentException("Cannot animate " + animatedProperty.getName() + " on RenderThread");
}
