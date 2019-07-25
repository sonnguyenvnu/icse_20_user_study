public void left(double turnAngle){
  turtleDirection+=turnAngle;
  while (turtleDirection > 360) {
    turtleDirection-=360;
  }
  while (turtleDirection < 0) {
    turtleDirection+=360;
  }
}
