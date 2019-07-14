public void pan(Point translation){
  position.x+=translation.x;
  position.y+=translation.y;
  updatePosition();
}
