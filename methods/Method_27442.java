private void appendWatch(SpannableBuilder spannableBuilder,EventsType type,Event eventsModel){
  spannableBuilder.bold(resources.getString(type.getType()).toLowerCase()).append(" ").append(eventsModel.getRepo().getName());
}
