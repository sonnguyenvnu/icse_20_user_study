public boolean pick(int mx,int my){
  if (!visible) {
    return false;
  }
  if (mx > x && mx < x + width && my > y && my < y + height) {
    return true;
  }
  return false;
}
