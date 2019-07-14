protected void enableTexturing(int target){
  if (target == TEXTURE_2D) {
    texturingTargets[0]=true;
  }
 else   if (target == TEXTURE_RECTANGLE) {
    texturingTargets[1]=true;
  }
}
