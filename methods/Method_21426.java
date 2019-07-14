public Date getActivePeakTime(){
  if (activePeakTime <= 0) {
    return null;
  }
  return new Date(activePeakTime);
}
