public int compareNewer(Score other){
  return getTimestamp().compare(other.getTimestamp());
}
