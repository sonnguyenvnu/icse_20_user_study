private void problem(Severity severity,int lineNumber,String filePath,String detailMessage,Object... args){
  String message=args.length == 0 ? detailMessage : String.format(detailMessage,args);
  problemsBySeverity.put(severity,String.format("%s:%s:%s: %s",severity.getMessagePrefix(),filePath.substring(filePath.lastIndexOf('/') + 1),lineNumber,message));
}
