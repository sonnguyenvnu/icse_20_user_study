private static void bufferappend(final StringBuilder buffer,int keylen,final String key,Object value){
  if (value instanceof Double)   bufferappend(buffer,keylen,key,((Double)value).toString());
 else   if (value instanceof Number)   bufferappend(buffer,keylen,key,((Number)value).longValue());
 else   bufferappend(buffer,keylen,key,value.toString());
}
