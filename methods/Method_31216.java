private void execute(Connection connection,Callback callback,Event event,Context context){
  connection.restoreOriginalState();
  connection.changeCurrentSchemaTo(schema);
  handleEvent(callback,event,context);
}
