public int index(@NotNull SEnumerationLiteral literal){
  SEnumerationLiteralId literalId=MetaIdHelper.getEnumerationLiteral(literal);
  if (!literalId.getEnumerationId().equals(myEnumerationId)) {
    return -1;
  }
  return myIndex.get(literalId.getIdValue());
}
