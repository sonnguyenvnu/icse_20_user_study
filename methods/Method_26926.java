/** 
 * @hide
 */
@Override protected void createAnimators(@NonNull ViewGroup sceneRoot,@NonNull TransitionValuesMaps startValues,@NonNull TransitionValuesMaps endValues,@NonNull ArrayList<TransitionValues> startValuesList,@NonNull ArrayList<TransitionValues> endValuesList){
  long startDelay=getStartDelay();
  int numTransitions=mTransitions.size();
  for (int i=0; i < numTransitions; i++) {
    Transition childTransition=mTransitions.get(i);
    if (startDelay > 0 && (mPlayTogether || i == 0)) {
      long childStartDelay=childTransition.getStartDelay();
      if (childStartDelay > 0) {
        childTransition.setStartDelay(startDelay + childStartDelay);
      }
 else {
        childTransition.setStartDelay(startDelay);
      }
    }
    childTransition.createAnimators(sceneRoot,startValues,endValues,startValuesList,endValuesList);
  }
}
