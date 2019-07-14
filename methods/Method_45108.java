private Function<Map.Entry<String,String[]>,String[]> toValue(){
  return new Function<Map.Entry<String,String[]>,String[]>(){
    @Override public String[] apply(    final Map.Entry<String,String[]> input){
      return input.getValue();
    }
  }
;
}
