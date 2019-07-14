protected void disableTexturing(int target){
  if (target == TEXTURE_2D) {
    texturingTargets[0]=false;
  }
 else   if (target == TEXTURE_RECTANGLE) {
    texturingTargets[1]=false;
  }
}
