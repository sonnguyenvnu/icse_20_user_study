public static UGraphic stroke(UGraphic ug,double dashVisible,double dashSpace,double thickness){
  return ug.apply(new UStroke(dashVisible,dashSpace,thickness));
}
