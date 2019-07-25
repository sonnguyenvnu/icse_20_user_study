public static void exec(String cmd,StringBuilder out,StringBuilder err) throws IOException {
  exec(Strings.splitIgnoreBlank(cmd," "),Encoding.CHARSET_UTF8,out,err);
}
