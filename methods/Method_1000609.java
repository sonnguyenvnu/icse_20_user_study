public static void exec(String[] cmd,StringBuilder out,StringBuilder err) throws IOException {
  exec(cmd,Encoding.CHARSET_UTF8,out,err);
}
