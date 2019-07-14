protected Object parseRest(DefaultJSONParser parser,Type type,Object fieldName,Object instance,int features,int[] setFlags){
  Object value=deserialze(parser,type,fieldName,instance,features,setFlags);
  return value;
}
