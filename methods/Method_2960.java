@Override public int compareTo(Object o){
  if (equals(o))   return 0;
  return hashCode() - o.hashCode();
}
