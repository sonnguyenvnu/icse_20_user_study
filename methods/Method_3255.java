@Override protected void convertCorpus(Sentence sentence,BufferedWriter bw) throws IOException {
  List<String[]> collector=Utility.convertSentenceToNER(sentence,tagSet);
  for (  String[] tuple : collector) {
    bw.write(tuple[0]);
    bw.write('\t');
    bw.write(tuple[1]);
    bw.write('\t');
    bw.write(tuple[2]);
    bw.newLine();
  }
}
