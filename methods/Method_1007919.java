public void run(){
  ProgressListener pl=new ProgressListenerImpl(folder,frame);
  pl.onMigrationBegin();
  ProjectConverter converter=new ProjectConverter(conversionType,pl);
  converter.convertProject(folder);
}
