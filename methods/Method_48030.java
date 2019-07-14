public static long[] splitLongs(String str){
  String parts[]=org.apache.commons.lang3.StringUtils.split(str,',');
  long numbers[]=new long[parts.length];
  for (int i=0; i < parts.length; i++)   numbers[i]=Long.valueOf(parts[i]);
  return numbers;
}
