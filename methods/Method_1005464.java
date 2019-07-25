public List<CopyByReference> build(){
  List<CopyByReference> answer=new ArrayList<>();
  for (  String current : copyByReference) {
    answer.add(new CopyByReference(current));
  }
  return answer;
}
