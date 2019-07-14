private static Integer[] parseParamsAsInts(String hintAsString,String startWith){
  String[] number=getParamsFromHint(hintAsString,startWith);
  if (number == null) {
    return new Integer[0];
  }
  Integer[] params=new Integer[number.length];
  for (int i=0; i < params.length; i++) {
    params[i]=Integer.parseInt(number[i]);
  }
  return params;
}
