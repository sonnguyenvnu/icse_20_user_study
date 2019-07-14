private void removePart(ArrayList<Range> ranges,int start,int end){
  if (ranges == null || end < start) {
    return;
  }
  int count=ranges.size();
  Range range;
  boolean modified=false;
  for (int a=0; a < count; a++) {
    range=ranges.get(a);
    if (start == range.end) {
      range.end=end;
      modified=true;
      break;
    }
 else     if (end == range.start) {
      range.start=start;
      modified=true;
      break;
    }
  }
  if (!modified) {
    ranges.add(new Range(start,end));
  }
}
