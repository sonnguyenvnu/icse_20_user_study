/** 
 * Resets creating generated columns.
 */
public Q resetGeneratedColumns(){
  checkCreated();
  generatedColumns=null;
  return _this();
}
