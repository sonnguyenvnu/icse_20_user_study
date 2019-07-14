private static void pruneOverlaps(List<Link> links){
  Collections.sort(links);
  int i=0;
  int last=links.size() - 1;
  while (i < last) {
    Link a=links.get(i);
    Link b=links.get(i + 1);
    int remove=-1;
    if ((a.start <= b.start) && (a.end > b.start)) {
      if (b.end <= a.end) {
        remove=i + 1;
      }
 else       if ((a.end - a.start) > (b.end - b.start)) {
        remove=i + 1;
      }
 else       if ((a.end - a.start) < (b.end - b.start)) {
        remove=i;
      }
      if (remove != -1) {
        links.remove(remove);
        --last;
        continue;
      }
    }
    ++i;
  }
}
