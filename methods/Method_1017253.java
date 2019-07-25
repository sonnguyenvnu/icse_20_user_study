@Override public void open(Serializable previousCheckpoint) throws Exception {
  if (previousCheckpoint == null) {
    this.checkpoint=new ChunkCheckpoint();
  }
 else {
    this.checkpoint=(ChunkCheckpoint)previousCheckpoint;
  }
  br=new BufferedReader(new FileReader(new File(System.getProperty("java.io.tmpdir"),fileName)));
  long lineNumber=checkpoint.getLineNumber();
  if (lineNumber > 0) {
    log.info("Skipping to line " + lineNumber + " as marked by previous checkpoint");
  }
  for (long i=0; i < lineNumber; i++) {
    br.readLine();
  }
}
