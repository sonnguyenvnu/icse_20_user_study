public void add(WarmerStats warmerStats){
  if (warmerStats == null) {
    return;
  }
  this.current+=warmerStats.current;
  this.total+=warmerStats.total;
  this.totalTimeInMillis+=warmerStats.totalTimeInMillis;
}
