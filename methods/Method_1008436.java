private static int step(CharacterRunAutomaton automaton,String key,int state){
  for (int i=0; state != -1 && i < key.length(); ++i) {
    state=automaton.step(state,key.charAt(i));
  }
  return state;
}
