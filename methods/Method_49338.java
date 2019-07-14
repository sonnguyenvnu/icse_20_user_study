@Override public boolean isPoints(){
  return start != null && end != null && start.equals(end) && startInclusive && endInclusive;
}
