@Override public void execute(){
  List<FeedbackSessionAttributes> sessions=logic.getFeedbackSessionsWhichNeedOpenEmailsToBeSent();
  for (  FeedbackSessionAttributes session : sessions) {
    List<EmailWrapper> emailsToBeSent=emailGenerator.generateFeedbackSessionOpeningEmails(session);
    try {
      taskQueuer.scheduleEmailsForSending(emailsToBeSent);
      logic.updateFeedbackSession(FeedbackSessionAttributes.updateOptionsBuilder(session.getFeedbackSessionName(),session.getCourseId()).withSentOpenEmail(true).build());
    }
 catch (    Exception e) {
      log.severe("Unexpected error: " + TeammatesException.toStringWithStackTrace(e));
    }
  }
}
