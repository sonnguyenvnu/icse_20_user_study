private void appendForkApplyEvent(SpannableBuilder spannableBuilder,Event eventsModel){
  spannableBuilder.bold(eventsModel.getPayload().getHead()).append(" ").append(eventsModel.getPayload().getBefore()).append(" ").append(eventsModel.getRepo() != null ? "in " + eventsModel.getRepo().getName() : "");
}
