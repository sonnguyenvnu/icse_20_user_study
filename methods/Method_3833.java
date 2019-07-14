void ensureLayoutState(){
  if (mLayoutState == null) {
    mLayoutState=createLayoutState();
  }
}
