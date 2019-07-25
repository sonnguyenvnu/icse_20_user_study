public LivingSpace previous(LivingSpace element){
  LivingSpace previous=null;
  for (  LivingSpace current : all.values()) {
    if (current == element) {
      return previous;
    }
    previous=current;
  }
  return null;
}
