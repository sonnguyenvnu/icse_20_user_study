@Override public RValue optimize() throws EvaluationException {
  final RValue optimizedParameter=parameter.optimize();
  final List<RValue> newSequence=new ArrayList<>();
  if (optimizedParameter instanceof Constant) {
    final double parameter=optimizedParameter.getValue();
    final Integer index=valueMap.get(parameter);
    if (index == null) {
      return defaultCase == null ? new Constant(getPosition(),0.0) : defaultCase.optimize();
    }
    boolean breakDetected=false;
    for (int i=index; i < caseStatements.length && !breakDetected; ++i) {
      final RValue invokable=caseStatements[i].optimize();
      if (invokable instanceof Sequence) {
        for (        RValue subInvokable : ((Sequence)invokable).sequence) {
          if (subInvokable instanceof Break) {
            breakDetected=true;
            break;
          }
          newSequence.add(subInvokable);
        }
      }
 else {
        newSequence.add(invokable);
      }
    }
    if (defaultCase != null && !breakDetected) {
      final RValue invokable=defaultCase.optimize();
      if (invokable instanceof Sequence) {
        Collections.addAll(newSequence,((Sequence)invokable).sequence);
      }
 else {
        newSequence.add(invokable);
      }
    }
    return new Switch(getPosition(),optimizedParameter,Collections.singletonMap(parameter,0),newSequence,null);
  }
  final Map<Double,Integer> newValueMap=new HashMap<>();
  Map<Integer,Double> backMap=new HashMap<>();
  for (  Entry<Double,Integer> entry : valueMap.entrySet()) {
    backMap.put(entry.getValue(),entry.getKey());
  }
  for (int i=0; i < caseStatements.length; ++i) {
    final RValue invokable=caseStatements[i].optimize();
    final Double caseValue=backMap.get(i);
    if (caseValue != null) {
      newValueMap.put(caseValue,newSequence.size());
    }
    if (invokable instanceof Sequence) {
      Collections.addAll(newSequence,((Sequence)invokable).sequence);
    }
 else {
      newSequence.add(invokable);
    }
  }
  return new Switch(getPosition(),optimizedParameter,newValueMap,newSequence,defaultCase.optimize());
}
