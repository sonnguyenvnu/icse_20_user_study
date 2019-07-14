/** 
 * {@inheritDoc}
 */
@Override protected void interpolate(double frac){
  if (start == null) {
    starting();
  }
  Color newColor=start.interpolate(end,frac);
  if (Color.TRANSPARENT.equals(start)) {
    newColor=new Color(end.getRed(),end.getGreen(),end.getBlue(),newColor.getOpacity());
  }
  region.get().setBackground(new Background(new BackgroundFill(newColor,radii,insets)));
}
