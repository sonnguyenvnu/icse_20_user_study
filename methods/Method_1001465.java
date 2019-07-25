public Ftile activity(Display label,Swimlane swimlane,BoxStyle style,Colors colors){
  final UFont font=skinParam.getFont(null,false,FontParam.ACTIVITY);
  return new FtileBox(colors.mute(skinParam),label,font,swimlane,style);
}
