public static boolean isMove(Location location,Location preLocation){
  boolean isMove;
  if (preLocation != null) {
    double speed=location.getSpeed() * 3.6;
    double distance=location.distanceTo(preLocation);
    double compass=Math.abs(preLocation.getBearing() - location.getBearing());
    double angle;
    if (compass > 180) {
      angle=360 - compass;
    }
 else {
      angle=compass;
    }
    if (speed != 0) {
      if (speed < 35 && (distance > 3 && distance < 10)) {
        isMove=angle > 10;
      }
 else {
        isMove=(speed < 40 && distance > 10 && distance < 100) || (speed < 50 && distance > 10 && distance < 100) || (speed < 60 && distance > 10 && distance < 100) || (speed < 9999 && distance > 100);
      }
    }
 else {
      isMove=false;
    }
  }
 else {
    isMove=true;
  }
  return isMove;
}
