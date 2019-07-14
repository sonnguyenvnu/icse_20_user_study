public void setRotation(int angle){
  if (angle == 0) {
    matrix=Matrix.ROTATE_0;
  }
 else   if (angle == 90) {
    matrix=Matrix.ROTATE_90;
  }
 else   if (angle == 180) {
    matrix=Matrix.ROTATE_180;
  }
 else   if (angle == 270) {
    matrix=Matrix.ROTATE_270;
  }
}
