@Override public List<Term> flow(String input){
  List<IWord> i=first.flow(input);
  for (  Pipe<List<IWord>,List<IWord>> pipe : pipeList) {
    i=pipe.flow(i);
  }
  return last.flow(i);
}
