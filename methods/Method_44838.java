public RippleNotifications getNotifications(final String account,final Boolean excludeFailed,final Boolean earliestFirst,final Integer resultsPerPage,final Integer page,final Long startLedger,final Long endLedger) throws RippleException, IOException {
  return ripplePublic.notifications(account,excludeFailed,earliestFirst,resultsPerPage,page,startLedger,endLedger);
}
