public String getLogcat() throws IOException {
  int maxLineCount=250;
  StringBuilder builder=new StringBuilder();
  String[] command=new String[]{"logcat","-d"};
  java.lang.Process process=Runtime.getRuntime().exec(command);
  InputStreamReader in=new InputStreamReader(process.getInputStream());
  BufferedReader bufferedReader=new BufferedReader(in);
  LinkedList<String> log=new LinkedList<>();
  String line;
  while ((line=bufferedReader.readLine()) != null) {
    log.addLast(line);
    if (log.size() > maxLineCount)     log.removeFirst();
  }
  for (  String l : log) {
    builder.append(l);
    builder.append('\n');
  }
  return builder.toString();
}
