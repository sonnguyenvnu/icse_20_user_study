static String guessBetterFormat(ImportingJob job,ArrayNode fileRecords,String bestFormat){
  if (bestFormat != null && fileRecords != null && fileRecords.size() > 0) {
    ObjectNode firstFileRecord=JSONUtilities.getObjectElement(fileRecords,0);
    String encoding=getEncoding(firstFileRecord);
    String location=JSONUtilities.getString(firstFileRecord,"location",null);
    if (location != null) {
      File file=new File(job.getRawDataDir(),location);
      while (true) {
        String betterFormat=null;
        List<FormatGuesser> guessers=ImportingManager.formatToGuessers.get(bestFormat);
        if (guessers != null) {
          for (          FormatGuesser guesser : guessers) {
            betterFormat=guesser.guess(file,encoding,bestFormat);
            if (betterFormat != null) {
              break;
            }
          }
        }
        if (betterFormat != null && !betterFormat.equals(bestFormat)) {
          bestFormat=betterFormat;
        }
 else {
          break;
        }
      }
    }
  }
  return bestFormat;
}
