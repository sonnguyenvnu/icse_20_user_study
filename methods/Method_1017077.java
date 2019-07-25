public void add(Set<Series> series){
  this.seriesSize.add(series.size());
  for (  final Series s : series) {
    uniqueKeys.add(s.getKey());
    for (    Map.Entry<String,String> e : s.getTags().entrySet()) {
      tags.put(e.getKey(),e.getValue());
    }
    for (    Map.Entry<String,String> e : s.getResource().entrySet()) {
      resource.put(e.getKey(),e.getValue());
    }
  }
}
