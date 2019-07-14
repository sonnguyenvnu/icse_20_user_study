private void setModeOrColor(Paint p,PorterDuffXfermode mode,int color){
  if (isTransparencyEnabled)   p.setXfermode(mode);
 else   p.setColor(color);
}
