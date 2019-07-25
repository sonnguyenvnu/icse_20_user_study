@Override public void compare(){
  List<Redis> currentAll=getAll(current);
  List<Redis> futureAll=getAll(future);
  Pair<List<Redis>,List<Pair<Redis,Redis>>> subResult=sub(futureAll,currentAll);
  List<Redis> tAdded=subResult.getKey();
  added.addAll(tAdded);
  List<Pair<Redis,Redis>> modified=subResult.getValue();
  compareConfigConfig(modified);
  List<Redis> tRemoved=sub(currentAll,futureAll).getKey();
  removed.addAll(tRemoved);
}
