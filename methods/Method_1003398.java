private void run(String dictionaryFileName,String dir) throws IOException {
  process(new File(dictionaryFileName));
  process(new File(dir));
  HashSet<String> unused=new HashSet<>();
  unused.addAll(dictionary);
  unused.removeAll(used);
  if (printDictionary) {
    System.out.println("USED WORDS");
    String[] list=used.toArray(new String[used.size()]);
    Arrays.sort(list);
    StringBuilder buff=new StringBuilder();
    for (    String s : list) {
      if (buff.length() > 0) {
        if (buff.length() + s.length() > 80) {
          System.out.println(buff.toString());
          buff.setLength(0);
        }
 else {
          buff.append(' ');
        }
      }
      buff.append(s);
    }
    System.out.println(buff.toString());
  }
  if (unknown.size() > 0) {
    System.out.println();
    System.out.println("UNKNOWN WORDS");
    for (    String s : unknown.keySet()) {
      System.out.print(s + " ");
      errorCount++;
    }
    System.out.println();
    System.out.println();
  }
  if (errorCount > 0) {
    throw new IOException(errorCount + " error found");
  }
}
