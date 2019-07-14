public void onExportCSV(){
  List<Habit> selected=new LinkedList<>();
  for (  Habit h : habitList)   selected.add(h);
  File outputDir=dirFinder.getCSVOutputDir();
  taskRunner.execute(new ExportCSVTask(habitList,selected,outputDir,filename -> {
    if (filename != null)     screen.showSendFileScreen(filename);
 else     screen.showMessage(Message.COULD_NOT_EXPORT);
  }
));
}
