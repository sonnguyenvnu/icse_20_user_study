@Override public final <O>OperationChain<O> optimise(final OperationChain<O> operationChain){
  final List<Operation> ops=operationChain.getOperations();
  final int numOps=ops.size();
  if (numOps == 0) {
    return operationChain;
  }
  final List<Operation> optimisedOps=new ArrayList<>();
  Operation previousOp;
  Operation currentOp=null;
  Operation nextOp=ops.get(0);
  for (int index=0; index < numOps; index++) {
    previousOp=currentOp;
    currentOp=nextOp;
    nextOp=((index + 1) < numOps) ? ops.get(index + 1) : null;
    optimisedOps.addAll(addPreOperations(previousOp,currentOp));
    optimisedOps.addAll(optimiseCurrentOperation(previousOp,currentOp,nextOp));
    optimisedOps.addAll(addPostOperations(currentOp,nextOp));
  }
  return new OperationChain<>(optimiseAll(optimisedOps));
}
