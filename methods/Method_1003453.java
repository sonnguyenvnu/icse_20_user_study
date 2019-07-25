private void run(LineNumberReader reader,PrintWriter writer) throws IOException {
  StringBuilder buff=new StringBuilder();
  while (true) {
    String line=reader.readLine();
    if (line == null) {
      break;
    }
    buff.append(line).append('\n');
    if (line.trim().length() == 0) {
      writer.print(filter(buff.toString()));
      buff=new StringBuilder();
    }
  }
  writer.println(filter(buff.toString()));
}
