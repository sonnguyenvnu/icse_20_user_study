@Override public Iterator<Mapper> iterator(){
  return Collections.<Mapper>singleton(parentJoinField).iterator();
}
