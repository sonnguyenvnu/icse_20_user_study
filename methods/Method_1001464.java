public Ftile spot(Swimlane swimlane,String spot){
  final UFont font=skinParam.getFont(null,false,FontParam.ACTIVITY);
  return new FtileCircleSpot(skinParam(),swimlane,spot,font);
}
