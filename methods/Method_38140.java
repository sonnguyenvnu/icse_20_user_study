/** 
 * Specifies that database will generate some columns values, usually the single id.
 */
public Q setGeneratedKey(){
  setGeneratedColumns();
  return _this();
}
