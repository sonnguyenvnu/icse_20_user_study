public boolean swapIcon(int icon){
  if (mediaActionDrawable.setIcon(icon,false)) {
    return true;
  }
  return false;
}
