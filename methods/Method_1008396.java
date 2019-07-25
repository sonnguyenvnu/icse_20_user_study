public static void spaceify(int spaces,String from,StringBuilder to) throws Exception {
  try (BufferedReader reader=new BufferedReader(new FastStringReader(from))){
    String line;
    while ((line=reader.readLine()) != null) {
      for (int i=0; i < spaces; i++) {
        to.append(' ');
      }
      to.append(line).append('\n');
    }
  }
 }
