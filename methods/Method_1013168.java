/** 
 * Save data back to model
 */
public void commit(boolean onSave){
  final String expression=m_expressionInput.getDocument().get();
  getModel().unsavedSetEvalExpression(expression);
  super.commit(onSave);
}
