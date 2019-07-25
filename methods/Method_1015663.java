protected boolean decrement(long credits){
  if (min_credits - credits >= 0) {
    accumulated_credits+=credits;
    min_credits-=credits;
    return true;
  }
  return false;
}
