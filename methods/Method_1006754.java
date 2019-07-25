public String status(){
  return "#" + id + "(" + (countDown > 0 ? countDown : "LiftOff!") + "), ";
}
