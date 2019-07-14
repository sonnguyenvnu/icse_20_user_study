/** 
 * For the cost of an action given the gold dependencies For more information see: Yoav Goldberg and Joakim Nivre. "Training Deterministic Parsers with Non-Deterministic Oracles." TACL 1 (2013): 403-414.
 * @param action
 * @param dependency
 * @param state
 * @return oracle cost of the action
 * @throws Exception
 */
public int actionCost(Action action,int dependency,State state){
  if (!ArcEager.canDo(action,state))   return Integer.MAX_VALUE;
  int cost=0;
  if (action == Action.LeftArc) {
    int bufferHead=state.bufferHead();
    int stackHead=state.stackTop();
    if (goldDependencies.containsKey(stackHead) && goldDependencies.get(stackHead).headIndex == bufferHead && goldDependencies.get(stackHead).relationId != (dependency))     cost+=1;
  }
 else   if (action == Action.RightArc) {
    int bufferHead=state.bufferHead();
    int stackHead=state.stackTop();
    if (goldDependencies.containsKey(bufferHead) && goldDependencies.get(bufferHead).headIndex == stackHead && goldDependencies.get(bufferHead).relationId != (dependency))     cost+=1;
  }
  if (action == Action.Shift) {
    int bufferHead=state.bufferHead();
    for (    int stackItem : state.getStack()) {
      if (goldDependencies.containsKey(stackItem) && goldDependencies.get(stackItem).headIndex == (bufferHead))       cost+=1;
      if (goldDependencies.containsKey(bufferHead) && goldDependencies.get(bufferHead).headIndex == (stackItem))       cost+=1;
    }
  }
 else   if (action == Action.Reduce) {
    int stackHead=state.stackTop();
    if (!state.bufferEmpty())     for (int bufferItem=state.bufferHead(); bufferItem <= state.maxSentenceSize; bufferItem++) {
      if (goldDependencies.containsKey(bufferItem) && goldDependencies.get(bufferItem).headIndex == (stackHead))       cost+=1;
    }
  }
 else   if (action == Action.LeftArc && cost == 0) {
    int stackHead=state.stackTop();
    if (!state.bufferEmpty())     for (int bufferItem=state.bufferHead(); bufferItem <= state.maxSentenceSize; bufferItem++) {
      if (goldDependencies.containsKey(bufferItem) && goldDependencies.get(bufferItem).headIndex == (stackHead))       cost+=1;
      if (goldDependencies.containsKey(stackHead) && goldDependencies.get(stackHead).headIndex == (bufferItem))       if (bufferItem != state.bufferHead())       cost+=1;
    }
  }
 else   if (action == Action.RightArc && cost == 0) {
    int stackHead=state.stackTop();
    int bufferHead=state.bufferHead();
    for (    int stackItem : state.getStack()) {
      if (goldDependencies.containsKey(bufferHead) && goldDependencies.get(bufferHead).headIndex == (stackItem))       if (stackItem != stackHead)       cost+=1;
      if (goldDependencies.containsKey(stackItem) && goldDependencies.get(stackItem).headIndex == (bufferHead))       cost+=1;
    }
    if (!state.bufferEmpty())     for (int bufferItem=state.bufferHead(); bufferItem <= state.maxSentenceSize; bufferItem++) {
      if (goldDependencies.containsKey(bufferHead) && goldDependencies.get(bufferHead).headIndex == (bufferItem))       cost+=1;
    }
  }
  return cost;
}
