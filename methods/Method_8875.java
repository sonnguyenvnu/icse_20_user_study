private float getActualOuterRadius(){
  return (actualAreaSize.width > actualAreaSize.height ? actualAreaSize.height : actualAreaSize.width) * size;
}
