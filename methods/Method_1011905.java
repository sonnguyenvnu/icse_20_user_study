protected void perform(){
  if (canMove()) {
    moveKaja();
    pause();
  }
 else {
    reportError("Oops, There's a wall in front of me. I can't make a step forward.");
  }
  while (!(heading(Direction.south))) {
    try {
      turnLeft();
      pause();
    }
  finally {
    }
  }
  while (!(isWall())) {
    if (canMove()) {
      moveKaja();
      pause();
    }
 else {
      reportError("Oops, There's a wall in front of me. I can't make a step forward.");
    }
  }
  if (!(isFull())) {
    addMark();
    pause();
  }
 else {
    reportError("Cannot drop. The cell is already full.");
  }
  turn_SPACE_right_routine();
}
