static public void setColor(String attr,Color what){
  set(attr,"#" + PApplet.hex(what.getRGB() & 0xffffff,6));
}
