public boolean setMouseY(int my){
  boolean change=false;
  if (my > y && my < y + height) {
    if (!visible) {
      change=true;
    }
    visible=true;
  }
 else {
    if (visible) {
      change=true;
    }
    visible=false;
  }
  return change;
}
