/** 
 * Copy the data of the source column into the current column.
 * @param source the source column
 */
public void copy(Column source){
  checkConstraint=source.checkConstraint;
  checkConstraintSQL=source.checkConstraintSQL;
  name=source.name;
  nullable=source.nullable;
  defaultExpression=source.defaultExpression;
  onUpdateExpression=source.onUpdateExpression;
  originalSQL=source.originalSQL;
  convertNullToDefault=source.convertNullToDefault;
  sequence=source.sequence;
  comment=source.comment;
  computeTableFilter=source.computeTableFilter;
  isComputed=source.isComputed;
  selectivity=source.selectivity;
  primaryKey=source.primaryKey;
  visible=source.visible;
}
