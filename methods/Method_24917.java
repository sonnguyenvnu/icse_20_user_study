public void exceptionEvent(ExceptionEvent event){
  ObjectReference or=event.exception();
  ReferenceType rt=or.referenceType();
  String exceptionName=rt.name();
  Field messageField=rt.fieldByName("detailMessage");
  Value messageValue=or.getValue(messageField);
  int last=exceptionName.lastIndexOf('.');
  String message=exceptionName.substring(last + 1);
  if (messageValue != null) {
    String messageStr=messageValue.toString();
    if (messageStr.startsWith("\"")) {
      messageStr=messageStr.substring(1,messageStr.length() - 1);
    }
    message+=": " + messageStr;
  }
  reportException(message,or,event.thread());
  handleCommonErrors(exceptionName,message,listener,sketchErr);
  if (editor != null) {
    java.awt.EventQueue.invokeLater(() -> {
      editor.onRunnerExiting(Runner.this);
    }
);
  }
}
