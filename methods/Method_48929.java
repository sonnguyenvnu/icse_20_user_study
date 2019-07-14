public static int convertLimit(long limit){
  assert limit >= 0;
  if (limit >= Integer.MAX_VALUE)   return Integer.MAX_VALUE;
 else   return (int)limit;
}
