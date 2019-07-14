public void changePunc(String puncPath) throws Exception {
  BufferedReader reader=new BufferedReader(new FileReader(puncPath));
  punctuations=new HashSet<String>();
  String line;
  while ((line=reader.readLine()) != null) {
    line=line.trim();
    if (line.length() > 0)     punctuations.add(line.split(" ")[0].trim());
  }
}
