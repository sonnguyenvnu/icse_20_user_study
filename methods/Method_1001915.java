private void register(double dpiFactor){
  registerDriver(URectangle.class,new DriverRectangleG2d(dpiFactor,this));
  if (this.hasAffineTransform || dpiFactor != 1.0) {
    registerDriver(UText.class,new DriverTextAsPathG2d(this,TextBlockUtils.getFontRenderContext()));
  }
 else {
    registerDriver(UText.class,new DriverTextG2d(this));
  }
  registerDriver(ULine.class,new DriverLineG2d(dpiFactor));
  registerDriver(UPixel.class,new DriverPixelG2d());
  registerDriver(UPolygon.class,new DriverPolygonG2d(dpiFactor,this));
  registerDriver(UEllipse.class,new DriverEllipseG2d(dpiFactor,this));
  registerDriver(UImageSvg.class,new DriverImageG2d(dpiFactor,this));
  registerDriver(UImage.class,new DriverImageG2d(dpiFactor,this));
  registerDriver(DotPath.class,new DriverDotPathG2d(this));
  registerDriver(UPath.class,new DriverPathG2d(dpiFactor));
  registerDriver(UCenteredCharacter.class,new DriverCenteredCharacterG2d());
}
