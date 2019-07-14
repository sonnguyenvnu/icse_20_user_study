private void processCPDSuppression(final T currentToken){
  GenericToken comment=currentToken.getPreviousComment();
  while (comment != null) {
    if (comment.getImage().contains("CPD-OFF")) {
      discardingSuppressing=true;
      break;
    }
    if (comment.getImage().contains("CPD-ON")) {
      discardingSuppressing=false;
      break;
    }
    comment=comment.getPreviousComment();
  }
}
