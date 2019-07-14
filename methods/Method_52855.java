public static List<String> convertStringToListOfLines(String string) throws IOException {
  final List<String> lines=new ArrayList<>();
  try (BufferedReader reader=new BufferedReader(new StringReader(string))){
    String line=null;
    while ((line=reader.readLine()) != null) {
      lines.add(line);
    }
  }
   return lines;
}
