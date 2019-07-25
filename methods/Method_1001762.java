public Terminated<String> next(){
  final Terminated<String> result=data.get(i);
  i++;
  return result;
}
