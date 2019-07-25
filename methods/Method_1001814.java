public LivingSpace next(LivingSpace element){
  for (Iterator<LivingSpace> it=all.values().iterator(); it.hasNext(); ) {
    final LivingSpace current=it.next();
    if (current == element && it.hasNext()) {
      return it.next();
    }
  }
  return null;
}
