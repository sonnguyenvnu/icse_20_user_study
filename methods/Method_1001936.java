public StringLocated next(){
  final StringLocated result=data.get(nb);
  nb++;
  trace.add(result);
  return result;
}
