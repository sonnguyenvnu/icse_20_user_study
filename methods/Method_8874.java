private float getActualInnerRadius(){
  return (actualAreaSize.width > actualAreaSize.height ? actualAreaSize.height : actualAreaSize.width) * falloff;
}
