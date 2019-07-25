private static PaddedCell check(Formatter formatter,File file,String original,int maxLength){
  if (maxLength < 2) {
    throw new IllegalArgumentException("maxLength must be at least 2");
  }
  String appliedOnce=formatter.compute(original,file);
  if (appliedOnce.equals(original)) {
    return Type.CONVERGE.create(file,Collections.singletonList(appliedOnce));
  }
  String appliedTwice=formatter.compute(appliedOnce,file);
  if (appliedOnce.equals(appliedTwice)) {
    return Type.CONVERGE.create(file,Collections.singletonList(appliedOnce));
  }
  List<String> appliedN=new ArrayList<>();
  appliedN.add(appliedOnce);
  appliedN.add(appliedTwice);
  String input=appliedTwice;
  while (appliedN.size() < maxLength) {
    String output=formatter.compute(input,file);
    if (output.equals(input)) {
      return Type.CONVERGE.create(file,appliedN);
    }
 else {
      int idx=appliedN.indexOf(output);
      if (idx >= 0) {
        return Type.CYCLE.create(file,appliedN.subList(idx,appliedN.size()));
      }
 else {
        appliedN.add(output);
        input=output;
      }
    }
  }
  return Type.DIVERGE.create(file,appliedN);
}
