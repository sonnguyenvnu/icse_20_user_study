@Override public void doInBackground(){
  try {
    HabitsCSVExporter exporter;
    exporter=new HabitsCSVExporter(habitList,selectedHabits,outputDir);
    archiveFilename=exporter.writeArchive();
  }
 catch (  IOException e) {
    e.printStackTrace();
  }
}
