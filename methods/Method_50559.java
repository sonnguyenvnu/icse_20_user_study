private float[] getCorners(float corner){
  leftTopCorner[0]=0;
  leftTopCorner[1]=0;
  rightTopCorner[0]=0;
  rightTopCorner[1]=0;
  leftBottomCorner[0]=0;
  leftBottomCorner[1]=0;
  rightBottomCorner[0]=0;
  rightBottomCorner[1]=0;
  if (this.leftTopCornerEnable || this.rightTopCornerEnable || this.leftBottomCornerEnable || this.rightBottomCornerEnable) {
    if (this.leftTopCornerEnable) {
      leftTopCorner[0]=corner;
      leftTopCorner[1]=corner;
    }
    if (this.rightTopCornerEnable) {
      rightTopCorner[0]=corner;
      rightTopCorner[1]=corner;
    }
    if (this.leftBottomCornerEnable) {
      leftBottomCorner[0]=corner;
      leftBottomCorner[1]=corner;
    }
    if (this.rightBottomCornerEnable) {
      rightBottomCorner[0]=corner;
      rightBottomCorner[1]=corner;
    }
  }
 else {
    leftTopCorner[0]=corner;
    leftTopCorner[1]=corner;
    rightTopCorner[0]=corner;
    rightTopCorner[1]=corner;
    leftBottomCorner[0]=corner;
    leftBottomCorner[1]=corner;
    rightBottomCorner[0]=corner;
    rightBottomCorner[1]=corner;
  }
  corners[0]=leftTopCorner[0];
  corners[1]=leftTopCorner[1];
  corners[2]=rightTopCorner[0];
  corners[3]=rightTopCorner[1];
  corners[4]=rightBottomCorner[0];
  corners[5]=rightBottomCorner[1];
  corners[6]=leftBottomCorner[0];
  corners[7]=leftBottomCorner[1];
  return corners;
}
