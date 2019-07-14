/** 
 * Convert to a regular prop model. 
 */
public PropModel toPropModel(){
  final String localName=getName();
  return new PropModel(mParamModel,false,false,false,false,ResType.NONE,""){
    @Override public String getName(){
      return localName;
    }
  }
;
}
