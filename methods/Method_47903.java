public int compareLonger(Streak other){
  if (this.getLength() != other.getLength())   return Long.signum(this.getLength() - other.getLength());
  return compareNewer(other);
}
