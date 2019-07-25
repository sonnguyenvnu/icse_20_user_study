/** 
 * Try to replace the SQL statement of the view and re-compile this and all dependent views.
 * @param querySQL the SQL statement
 * @param newColumnTemplates the columns
 * @param session the session
 * @param recursive whether this is a recursive view
 * @param force if errors should be ignored
 * @param literalsChecked if literals have been checked
 */
public void replace(String querySQL,Column[] newColumnTemplates,Session session,boolean recursive,boolean force,boolean literalsChecked){
  String oldQuerySQL=this.querySQL;
  Column[] oldColumnTemplates=this.columnTemplates;
  boolean oldRecursive=this.allowRecursive;
  init(querySQL,null,newColumnTemplates,session,recursive,literalsChecked,isTableExpression);
  DbException e=recompile(session,force,true);
  if (e != null) {
    init(oldQuerySQL,null,oldColumnTemplates,session,oldRecursive,literalsChecked,isTableExpression);
    recompile(session,true,false);
    throw e;
  }
}
