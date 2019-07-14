public boolean isFunded(){
  return isLive() && (percentageFunded() >= 100);
}
