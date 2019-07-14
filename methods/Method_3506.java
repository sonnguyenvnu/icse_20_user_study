public Term next() throws IOException {
  if (termArray != null && index < termArray.length)   return termArray[index++];
  String line=br.readLine();
  while (TextUtility.isBlank(line)) {
    if (line == null)     return null;
    line=br.readLine();
  }
  List<Term> termList=segment.seg(line);
  if (termList.size() == 0)   return null;
  termArray=termList.toArray(new Term[0]);
  index=0;
  return termArray[index++];
}
