protected static boolean isHighestPriority(LineHighlight hl){
  for (  LineHighlight check : allHighlights) {
    if (check.getLineID().equals(hl.getLineID()) && check.priority() > hl.priority()) {
      return false;
    }
  }
  return true;
}
