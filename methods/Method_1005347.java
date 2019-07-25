public void dim(int level){
  this.level=level;
  if (level == 0) {
    off();
  }
 else {
    System.out.println("Light is dimmed to " + level + "%");
  }
}
