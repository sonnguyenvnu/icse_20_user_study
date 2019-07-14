public static Predicate<Map.Entry<String,String[]>> isForHeaderName(final String key){
  return new Predicate<Map.Entry<String,String[]>>(){
    @Override public boolean apply(    final Map.Entry<String,String[]> input){
      return isSameHeaderName(input.getKey(),key);
    }
  }
;
}
