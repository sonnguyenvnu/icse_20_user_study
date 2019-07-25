private boolean flipped(Calendar last,Calendar current){
  if (last == null) {
    return true;
  }
  if (last.get(Calendar.YEAR) != current.get(Calendar.YEAR)) {
    return true;
  }
  if (last.get(Calendar.MONTH) != current.get(Calendar.MONTH)) {
    return true;
  }
  if (last.get(Calendar.DAY_OF_MONTH) != current.get(Calendar.DAY_OF_MONTH)) {
    return true;
  }
  if (last.get(Calendar.HOUR_OF_DAY) != current.get(Calendar.HOUR_OF_DAY)) {
    return true;
  }
  return false;
}
