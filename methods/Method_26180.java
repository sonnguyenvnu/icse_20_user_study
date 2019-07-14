private static Optional<Nullness> nullnessFromAnnotations(VarSymbol param){
  Optional<Nullness> result=NullnessAnnotations.fromAnnotationsOn(param);
  if (result.isPresent()) {
    return result;
  }
  if (param.type instanceof TypeVariable) {
    return NullnessAnnotations.getUpperBound((TypeVariable)param.type);
  }
 else {
    return NullnessAnnotations.fromDefaultAnnotations(param);
  }
}
