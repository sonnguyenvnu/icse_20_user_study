public boolean hasMoreChildren(){
  return (getChild() != null ? getChild().getRemaining() : 0) < getRemaining();
}
