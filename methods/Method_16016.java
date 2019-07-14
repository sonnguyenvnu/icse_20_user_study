/** 
 * ?????http????: <pre> {name:"test",org:[1,2,3]} => {"name":"test","org[0]":1,"org[1]":2,"org[2]":3} </pre>
 * @param object
 * @return
 */
public static Map<String,String> objectToHttpParameters(Object object){
  return new HttpParameterConverter(object).convert();
}
