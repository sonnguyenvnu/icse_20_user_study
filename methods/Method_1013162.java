/** 
 * Save data back to config
 */
@Override public void commit(final boolean onSave){
  List<String> definitions=FormHelper.getSerializedInput(definitionsTable);
  getModel().setAttribute(MODEL_PARAMETER_DEFINITIONS,definitions);
  String newDefinitions=FormHelper.trimTrailingSpaces(newDefinitionsSource.getDocument().get());
  getModel().setAttribute(MODEL_PARAMETER_NEW_DEFINITIONS,newDefinitions);
  String modelValues=FormHelper.trimTrailingSpaces(modelValuesSource.getDocument().get());
  TypedSet modelValuesSet=TypedSet.parseSet(modelValues);
  getModel().setAttribute(MODEL_PARAMETER_MODEL_VALUES,modelValuesSet.toString());
  String constraintFormula=FormHelper.trimTrailingSpaces(constraintSource.getDocument().get());
  getModel().setAttribute(MODEL_PARAMETER_CONSTRAINT,constraintFormula);
  String actionConstraintFormula=FormHelper.trimTrailingSpaces(actionConstraintSource.getDocument().get());
  getModel().setAttribute(MODEL_PARAMETER_ACTION_CONSTRAINT,actionConstraintFormula);
  super.commit(onSave);
}
