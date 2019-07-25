@Override public void configure(StateMachineTransitionBuilder<S,E> builder) throws Exception {
  List<ChoiceData<S,E>> choices=new ArrayList<ChoiceData<S,E>>();
  if (first != null) {
    choices.add(first);
  }
  choices.addAll(thens);
  if (last != null) {
    choices.add(last);
  }
  builder.addChoice(source,choices);
}
