public static Tuple<String,String> round(String strColumn,String valueName){
  return mathSingleValueTemplate("Math.round","round",strColumn,valueName);
}
