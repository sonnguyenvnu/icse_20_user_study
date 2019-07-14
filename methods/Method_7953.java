private boolean intersect(float left1,float right1,float left2,float right2){
  if (left1 <= left2) {
    return right1 >= left2;
  }
  return left1 <= right2;
}
