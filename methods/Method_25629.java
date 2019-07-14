private String buildMessage(Type mockedClass,TypeSymbol forbiddenType,@Nullable TypeSymbol metaAnnotationType,T doNotMock){
  return String.format("%s; %s is annotated as @%s%s: %s.",buildMessage(mockedClass,forbiddenType),forbiddenType,metaAnnotationType == null ? annotationName : metaAnnotationType,(metaAnnotationType == null ? "" : String.format(" (which is annotated as @%s)",annotationName)),Optional.ofNullable(Strings.emptyToNull(getValueFunction.apply(doNotMock))).orElseGet(() -> String.format("It is annotated as %s.",annotationName)));
}
