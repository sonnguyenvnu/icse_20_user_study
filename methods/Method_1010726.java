@Override public void execute(EditorContext context){
  boolean before=myInsertBefore;
  EditorCell contextCell=context.getSelectedCell();
  myListHandler.insertNewChild(context,contextCell,before);
}
