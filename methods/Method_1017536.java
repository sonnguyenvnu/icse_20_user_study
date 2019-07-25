@Override public void configure(StateMachineStateBuilder<S,E> builder) throws Exception {
  Collection<StateData<S,E>> stateDatas=new ArrayList<StateData<S,E>>();
  for (  StateData<S,E> s : incomplete.values()) {
    s.setParent(parent);
    stateDatas.add(s);
    if (s.getState() == initialState) {
      s.setInitial(true);
      s.setInitialAction(initialAction);
    }
    if (ends.contains(s.getState())) {
      s.setEnd(true);
    }
    if (choices.contains(s.getState())) {
      s.setPseudoStateKind(PseudoStateKind.CHOICE);
    }
 else     if (junctions.contains(s.getState())) {
      s.setPseudoStateKind(PseudoStateKind.JUNCTION);
    }
 else     if (forks.contains(s.getState())) {
      s.setPseudoStateKind(PseudoStateKind.FORK);
    }
 else     if (joins.contains(s.getState())) {
      s.setPseudoStateKind(PseudoStateKind.JOIN);
    }
 else     if (entrys.contains(s.getState())) {
      s.setPseudoStateKind(PseudoStateKind.ENTRY);
    }
 else     if (exits.contains(s.getState())) {
      s.setPseudoStateKind(PseudoStateKind.EXIT);
    }
    if (s.getState() == history) {
      if (History.SHALLOW == historyType) {
        s.setPseudoStateKind(PseudoStateKind.HISTORY_SHALLOW);
      }
 else       if (History.DEEP == historyType) {
        s.setPseudoStateKind(PseudoStateKind.HISTORY_DEEP);
      }
    }
    s.setSubmachine(submachines.get(s.getState()));
    s.setSubmachineFactory(submachinefactories.get(s.getState()));
  }
  builder.addStateData(stateDatas);
}
