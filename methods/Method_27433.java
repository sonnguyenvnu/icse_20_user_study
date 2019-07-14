private void appendProjectCardEvent(SpannableBuilder spannableBuilder,Event eventsModel,boolean isColumn){
  spannableBuilder.bold(eventsModel.getPayload().getAction()).append(" ").append(!isColumn ? "project" : "column").append(" ").append(eventsModel.getRepo().getName());
}
