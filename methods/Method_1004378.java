private List<Message> head(List<Message> input,int head){
  if (head >= input.size()) {
    return input;
  }
  return input.subList(0,head);
}
