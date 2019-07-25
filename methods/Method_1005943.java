void bind(LogItem logItem){
  int logLevelColor=ResourcesCompat.getColor(resources,logItem.level.colorRes,null);
  logLevelView.setBackgroundColor(logLevelColor);
  logDateTextView.setText(dateFormat.format(logItem.date));
  logMsgTextView.setText(logItem.message);
  Linkify.addLinks(logMsgTextView,Linkify.ALL);
}
