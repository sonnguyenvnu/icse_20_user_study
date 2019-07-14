private void appendCreateEvent(SpannableBuilder spannableBuilder,Event eventsModel){
  PayloadModel payloadModel=eventsModel.getPayload();
  String refType=payloadModel.getRefType();
  spannableBuilder.bold("created").append(" ").append(refType).append(" ").append(!"repository".equalsIgnoreCase(refType) ? payloadModel.getRef() + " " : "").bold("at").append(" ").append(eventsModel.getRepo().getName());
  if (payloadModel.getDescription() != null) {
    MarkDownProvider.stripMdText(description,payloadModel.getDescription().replaceAll("\\r?\\n|\\r"," "));
    description.setVisibility(View.VISIBLE);
  }
 else {
    description.setText("");
    description.setVisibility(View.GONE);
  }
}
