public BlocLines trim(boolean removeEmptyLines){
  final List<StringLocated> copy=new ArrayList<StringLocated>(lines);
  for (int i=0; i < copy.size(); i++) {
    final StringLocated s=copy.get(i);
    copy.set(i,new StringLocated(s.getTrimmed().getString(),s.getLocation()));
  }
  if (removeEmptyLines) {
    for (final Iterator<StringLocated> it=copy.iterator(); it.hasNext(); ) {
      if (it.next().getString().length() == 0) {
        it.remove();
      }
    }
  }
  return new BlocLines(copy);
}
