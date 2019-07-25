<T extends Writer>T reconstruct(T sw,BufferedTokenStream tokenStream,int from,int to,Collection<Replacement> replacements,TemplateOptions templateOptions) throws IOException {
  ArrayList<Replacement> sorted=new ArrayList<>(replacements);
  Collections.sort(sorted,new Comparator<Replacement>(){
    @Override public int compare(    Replacement a,    Replacement b){
      return Integer.compare(a.interval.a,b.interval.b);
    }
  }
);
  for (int i=1; i < sorted.size(); i++) {
    Replacement previous=sorted.get(i - 1);
    Replacement current=sorted.get(i);
    if (!previous.interval.startsBeforeDisjoint(current.interval)) {
      throw new RuntimeException("Overlapping intervals: " + previous + " " + current);
    }
  }
  int left=from;
  for (  Replacement r : sorted) {
    int right=r.interval.a;
    for (int i=left; i < right; i++) {
      sw.append(tokenText(templateOptions,tokenStream.get(i)));
    }
    sw.append(r.replacement);
    left=r.interval.b + 1;
  }
  for (int i=left; i < to; i++) {
    sw.append(tokenText(templateOptions,tokenStream.get(i)));
  }
  return sw;
}
