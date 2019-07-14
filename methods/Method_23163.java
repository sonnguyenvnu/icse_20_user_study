@Override public void resetMatrix(){
  g2.setTransform(new AffineTransform());
  g2.scale(pixelDensity,pixelDensity);
}
