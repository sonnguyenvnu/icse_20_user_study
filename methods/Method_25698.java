public String describe(InvocationInfo info){
  return "The following arguments may have been swapped: " + changedPairs().stream().map(p -> String.format("'%s' for formal parameter '%s'",info.state().getSourceForNode(info.actualParameters().get(p.formal().index())),p.formal().name())).collect(Collectors.joining(", ")) + ". Either add clarifying `/* paramName= */` comments, or swap the arguments if that is" + " what was intended";
}
