public int size(){
  int count=0;
  for (Iterator<RuleViolation> i=iterator(); i.hasNext(); ) {
    i.next();
    count++;
  }
  return count;
}
