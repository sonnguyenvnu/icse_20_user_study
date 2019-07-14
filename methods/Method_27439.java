private void appendGistEvent(SpannableBuilder spannableBuilder,Event eventsModel){
  String action=eventsModel.getPayload().getAction();
  action="create".equals(action) ? "created" : "update".equals(action) ? "updated" : action;
  spannableBuilder.bold(action).append(" ").append(itemView.getResources().getString(R.string.gist)).append(" ").append(eventsModel.getPayload().getGist().getGistId());
}
