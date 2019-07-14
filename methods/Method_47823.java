/** 
 * Writes a scores file and a checkmarks file containing scores and checkmarks of every habit. The first column corresponds to the date. Subsequent columns correspond to a habit. Habits are taken from the list of selected habits. Dates are determined from the oldest repetition date to the newest repetition date found in the list of habits.
 * @throws IOException if there was problem writing the files
 */
private void writeMultipleHabits() throws IOException {
  String scoresFileName="Scores.csv";
  String checksFileName="Checkmarks.csv";
  generateFilenames.add(scoresFileName);
  generateFilenames.add(checksFileName);
  FileWriter scoresWriter=new FileWriter(exportDirName + scoresFileName);
  FileWriter checksWriter=new FileWriter(exportDirName + checksFileName);
  writeMultipleHabitsHeader(scoresWriter);
  writeMultipleHabitsHeader(checksWriter);
  Timestamp[] timeframe=getTimeframe();
  Timestamp oldest=timeframe[0];
  Timestamp newest=DateUtils.getToday();
  List<int[]> checkmarks=new ArrayList<>();
  List<double[]> scores=new ArrayList<>();
  for (  Habit h : selectedHabits) {
    checkmarks.add(h.getCheckmarks().getValues(oldest,newest));
    scores.add(h.getScores().getValues(oldest,newest));
  }
  int days=oldest.daysUntil(newest);
  SimpleDateFormat dateFormat=DateFormats.getCSVDateFormat();
  for (int i=0; i <= days; i++) {
    Date day=newest.minus(i).toJavaDate();
    String date=dateFormat.format(day);
    StringBuilder sb=new StringBuilder();
    sb.append(date).append(DELIMITER);
    checksWriter.write(sb.toString());
    scoresWriter.write(sb.toString());
    for (int j=0; j < selectedHabits.size(); j++) {
      checksWriter.write(String.valueOf(checkmarks.get(j)[i]));
      checksWriter.write(DELIMITER);
      String score=String.format("%.4f",((float)scores.get(j)[i]));
      scoresWriter.write(score);
      scoresWriter.write(DELIMITER);
    }
    checksWriter.write("\n");
    scoresWriter.write("\n");
  }
  scoresWriter.close();
  checksWriter.close();
}
