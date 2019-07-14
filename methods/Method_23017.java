static private void zoomStroke(Graphics2D g2){
  if (zoom != 1) {
    if (zoomStroke == null || zoomStroke.getLineWidth() != zoom) {
      zoomStroke=new BasicStroke(zoom);
    }
    g2.setStroke(zoomStroke);
  }
}
