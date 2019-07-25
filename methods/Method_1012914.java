@Override public void execute(){
  List<FeedbackSessionAttributes> sessions=logic.getFeedbackSessionsClosingWithinTimeLimit();
  for (  FeedbackSessionAttributes session : sessions) {
    List<EmailWrapper> emailsToBeSent=emailGenerator.generateFeedbackSessionClosingEmails(session);
    try {
      taskQueuer.scheduleEmailsForSending(emailsToBeSent);
      logic.updateFeedbackSession(FeedbackSessionAttributes.updateOptionsBuilder(session.getFeedbackSessionName(),session.getCourseId()).withSentClosingEmail(true).build());
    }
 catch (    Exception e) {
      log.severe("Unexpected error: " + TeammatesException.toStringWithStackTrace(e));
    }
  }
}
