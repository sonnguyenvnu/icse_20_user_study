/** 
 * ??http????
 * @return
 */
default String toHttpQueryParamString(){
  Map<String,String> result=new HttpParameterConverter(this).convert();
  StringJoiner joiner=new StringJoiner("&");
  result.forEach((key,value) -> joiner.add(key.concat("=").concat(value)));
  return joiner.toString();
}
