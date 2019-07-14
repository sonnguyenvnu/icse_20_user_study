public void setProgress(float progress){
  thumbX=(int)Math.ceil((width - thumbWidth) * progress);
  if (thumbX < 0) {
    thumbX=0;
  }
 else   if (thumbX > width - thumbWidth) {
    thumbX=width - thumbWidth;
  }
}
