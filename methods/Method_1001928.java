private void register(){
  registerDriver(URectangle.class,new DriverRectangleVdx());
  registerDriver(UText.class,new DriverTextVdx(stringBounder));
  registerDriver(AtomText.class,new DriverNoneVdx());
  registerDriver(ULine.class,new DriverLineVdx());
  registerDriver(UPolygon.class,new DriverPolygonVdx());
  registerDriver(UEllipse.class,new DriverNoneVdx());
  registerDriver(UImage.class,new DriverNoneVdx());
  registerDriver(UImageSvg.class,new DriverNoneVdx());
  registerDriver(UPath.class,new DriverUPathVdx());
  registerDriver(DotPath.class,new DriverDotPathVdx());
  registerDriver(UCenteredCharacter.class,new DriverNoneVdx());
}
