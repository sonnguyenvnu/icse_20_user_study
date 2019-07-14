@Override public void popStyle(){
  boolean savedSetAmbient=setAmbient;
  super.popStyle();
  if (!savedSetAmbient)   setAmbient=false;
}
