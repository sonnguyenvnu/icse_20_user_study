private List<PieEntry> createEntriesFromArray(long[] dataArray,boolean loading){
  long usedByFolder=dataArray[2], usedByOther=dataArray[0] - dataArray[1] - dataArray[2], freeSpace=dataArray[1];
  List<PieEntry> entries=new ArrayList<>();
  entries.add(new PieEntry(usedByFolder,LEGENDS[0],loading ? ">" : null));
  entries.add(new PieEntry(usedByOther,LEGENDS[1],loading ? "<" : null));
  entries.add(new PieEntry(freeSpace,LEGENDS[2]));
  return entries;
}
