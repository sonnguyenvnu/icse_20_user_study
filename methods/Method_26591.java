@Override public Boolean reduce(Boolean r1,Boolean r2){
  return firstNonNull(r1,true) && firstNonNull(r2,true);
}
