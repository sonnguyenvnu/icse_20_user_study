public static HttpParameter[] getParameterArray(String name1,String value1,String name2,String value2){
  return new HttpParameter[]{new HttpParameter(name1,value1),new HttpParameter(name2,value2)};
}
