public void updateMode(){
  Mode mode=editor.getMode();
  urlColor=mode.getColor("status.url.fgcolor");
  fgColor=new Color[]{mode.getColor("status.notice.fgcolor"),mode.getColor("status.error.fgcolor"),mode.getColor("status.error.fgcolor"),mode.getColor("status.warning.fgcolor"),mode.getColor("status.warning.fgcolor")};
  bgColor=new Color[]{mode.getColor("status.notice.bgcolor"),mode.getColor("status.error.bgcolor"),mode.getColor("status.error.bgcolor"),mode.getColor("status.warning.bgcolor"),mode.getColor("status.warning.bgcolor")};
  bgImage=new Image[]{mode.loadImage("/lib/status/notice.png"),mode.loadImage("/lib/status/error.png"),mode.loadImage("/lib/status/error.png"),mode.loadImage("/lib/status/warning.png"),mode.loadImage("/lib/status/warning.png")};
  font=mode.getFont("status.font");
  glyphFont=mode.getFont("status.emoji.font");
  metrics=null;
}
