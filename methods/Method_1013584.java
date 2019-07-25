public static String ppr(IValue v){
  if (v == null) {
    return "null";
  }
  return PrettyPrint.mypp(v.toString(),80);
}
