private void _XXXXX_(final PrintWriter pw,final String msg,final int i){
  pw.println(msg + ": '" + (char)(0xff & (i >> 24))+ (char)(0xff & (i >> 16))+ (char)(0xff & (i >> 8))+ (char)(0xff & (i >> 0))+ "'");
}