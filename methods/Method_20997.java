public boolean isApproachingDeadline(){
  if (deadline().isBeforeNow()) {
    return false;
  }
  return deadline().isBefore(new DateTime().plusDays(2));
}
