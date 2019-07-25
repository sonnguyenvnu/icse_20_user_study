/** 
 * Save data back to model
 */
public void commit(boolean onSave){
  if (expressionEvalInput != null) {
    final String expression=expressionEvalInput.getDocument().get();
    getModel().unsavedSetEvalExpression(expression);
  }
  super.commit(onSave);
}
