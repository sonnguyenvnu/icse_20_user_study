public boolean includes(Group other){
  if (other.myMappings.size() > myMappings.size()) {
    return false;
  }
  if (other.isEmpty()) {
    return isEmpty();
  }
  return myMappings.containsAll(other.myMappings);
}
