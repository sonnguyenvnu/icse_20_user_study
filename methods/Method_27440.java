private void appendFollowEvent(SpannableBuilder spannableBuilder,Event eventsModel){
  spannableBuilder.bold("started following").append(" ").bold(eventsModel.getPayload().getTarget().getLogin());
}
