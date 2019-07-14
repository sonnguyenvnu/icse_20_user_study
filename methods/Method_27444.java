private void appendActor(@NonNull Event eventsModel,SpannableBuilder spannableBuilder){
  if (eventsModel.getActor() != null) {
    spannableBuilder.append(eventsModel.getActor().getLogin()).append(" ");
  }
}
