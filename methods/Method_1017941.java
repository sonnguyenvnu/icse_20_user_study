/** 
 * Run the thread
 */
public void run(){
  BufferedReader bufferedReader;
  bufferedReader=new BufferedReader(new InputStreamReader(inputStream,StandardCharsets.UTF_8));
  String line;
  try {
    while ((line=bufferedReader.readLine()) != null) {
      String SEARCH_STRING_FOR_RETRIEVED_RECORDS_FOUND="INFO mapreduce.ImportJobBase: Retrieved";
      String SEARCH_STRING_FOR_NO_NEW_RECORDS_FOUND="No new rows detected since last import";
      if ((!logLineWithRetrievedRecordsFound) && (line.contains(SEARCH_STRING_FOR_RETRIEVED_RECORDS_FOUND) || line.contains(SEARCH_STRING_FOR_NO_NEW_RECORDS_FOUND))) {
        logLineWithRetrievedRecordsFound=true;
        logLines[0]=line;
        latch.countDown();
      }
      if (this.sourceLoadStrategy != SqoopLoadStrategy.FULL_LOAD) {
        String SEARCH_STRING_FOR_NEW_HIGH_WATERMARK_FOUND="INFO tool.ImportTool:   --last-value";
        if ((!logLineWithNewHighWaterMarkFound) && (line.contains(SEARCH_STRING_FOR_NEW_HIGH_WATERMARK_FOUND))) {
          logLineWithNewHighWaterMarkFound=true;
          logLines[1]=line;
          latch.countDown();
        }
      }
      logger.info(line);
    }
  }
 catch (  IOException ioe) {
    logger.warn("I/O error occurred while handling stream. [{}]",new Object[]{ioe.getMessage()});
  }
catch (  Throwable t) {
    logger.warn("An error occurred handling stream. [{}]",new Object[]{t.getMessage()});
  }
 finally {
    for (long i=0; i < latch.getCount(); i++) {
      latch.countDown();
    }
    try {
      bufferedReader.close();
    }
 catch (    IOException ioe) {
      logger.warn("I/O error closing buffered reader for stream");
    }
  }
}
