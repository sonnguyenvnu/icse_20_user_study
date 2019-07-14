private void writeCheckmarks(String habitDirName,CheckmarkList checkmarks) throws IOException {
  String filename=habitDirName + "Checkmarks.csv";
  FileWriter out=new FileWriter(exportDirName + filename);
  generateFilenames.add(filename);
  checkmarks.writeCSV(out);
  out.close();
}
