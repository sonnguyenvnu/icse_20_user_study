default Operation next(Operation operation){
  return new DefaultOperation(flatMap(operation::promise));
}
