/** 
 * Finds DML operations in a given node descendants' path
 * @param node
 * @return true if found DML operations in node descendants
 */
static boolean foundAnyDML(final ApexNode<?> node){
  final List<ASTDmlUpsertStatement> dmlUpsertStatement=node.findDescendantsOfType(ASTDmlUpsertStatement.class);
  final List<ASTDmlUpdateStatement> dmlUpdateStatement=node.findDescendantsOfType(ASTDmlUpdateStatement.class);
  final List<ASTDmlUndeleteStatement> dmlUndeleteStatement=node.findDescendantsOfType(ASTDmlUndeleteStatement.class);
  final List<ASTDmlMergeStatement> dmlMergeStatement=node.findDescendantsOfType(ASTDmlMergeStatement.class);
  final List<ASTDmlInsertStatement> dmlInsertStatement=node.findDescendantsOfType(ASTDmlInsertStatement.class);
  final List<ASTDmlDeleteStatement> dmlDeleteStatement=node.findDescendantsOfType(ASTDmlDeleteStatement.class);
  return !dmlUpsertStatement.isEmpty() || !dmlUpdateStatement.isEmpty() || !dmlUndeleteStatement.isEmpty() || !dmlMergeStatement.isEmpty() || !dmlInsertStatement.isEmpty() || !dmlDeleteStatement.isEmpty();
}
