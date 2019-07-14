protected boolean texturingIsEnabled(int target){
  if (target == TEXTURE_2D) {
    return texturingTargets[0];
  }
 else   if (target == TEXTURE_RECTANGLE) {
    return texturingTargets[1];
  }
 else {
    return false;
  }
}
