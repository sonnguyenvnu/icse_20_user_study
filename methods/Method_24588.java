void complainAndQuit(String lastWords,boolean schoolEmFirst){
  if (schoolEmFirst) {
    printCommandLine(systemErr);
  }
  systemErr.println(lastWords);
  System.exit(1);
}
