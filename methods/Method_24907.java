boolean hasBadSize(){
  if (!width.equals("displayWidth") && !width.equals("displayHeight") && PApplet.parseInt(width,-1) == -1) {
    return true;
  }
  if (!height.equals("displayWidth") && !height.equals("displayHeight") && PApplet.parseInt(height,-1) == -1) {
    return true;
  }
  return false;
}
