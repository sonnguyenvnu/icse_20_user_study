public boolean pickText(int mx,int my){
  return (mx > x - 2 && mx < x + width + 2 && my > y - height && my < y);
}
