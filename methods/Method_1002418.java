static void dump(PrintStream os,byte[] bytes,int offset,int length){
  for (int i=offset; i < offset + length; ++i) {
    byte b=bytes[i];
    if (b >= 32 && b < 127) {
      os.print("'" + (char)b + "'");
    }
 else {
      os.print((int)b);
    }
    os.print(' ');
  }
}
