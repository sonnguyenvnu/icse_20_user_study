private void writeScores(String habitDirName,ScoreList scores) throws IOException {
  String path=habitDirName + "Scores.csv";
  FileWriter out=new FileWriter(exportDirName + path);
  generateFilenames.add(path);
  scores.writeCSV(out);
  out.close();
}
