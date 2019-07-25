@Override protected List<CopyFilesResultItemViewModel> call() throws InterruptedException, IOException {
  updateMessage(Localization.lang("Copying files..."));
  updateProgress(0,totalFilesCount);
  LocalDateTime currentTime=LocalDateTime.now();
  String currentDate=currentTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss"));
  try (BufferedWriter bw=Files.newBufferedWriter(exportPath.resolve(LOGFILE_PREFIX + currentDate + LOGFILE_EXT),StandardCharsets.UTF_8)){
    for (int i=0; i < entries.size(); i++) {
      if (isCancelled()) {
        break;
      }
      List<LinkedFile> files=entries.get(i).getFiles();
      for (int j=0; j < files.size(); j++) {
        if (isCancelled()) {
          break;
        }
        updateMessage(Localization.lang("Copying file %0 of entry %1",Integer.toString(j + 1),Integer.toString(i + 1)));
        LinkedFile fileName=files.get(j);
        Optional<Path> fileToExport=fileName.findIn(databaseContext,Globals.prefs.getFilePreferences());
        newPath=OptionalUtil.combine(Optional.of(exportPath),fileToExport,resolvePathFilename);
        if (newPath.isPresent()) {
          Path newFile=newPath.get();
          boolean success=FileUtil.copyFile(fileToExport.get(),newFile,false);
          updateProgress(totalFilesCounter++,totalFilesCount);
          try {
            Thread.sleep(300);
          }
 catch (          InterruptedException e) {
            if (isCancelled()) {
              updateMessage("Cancelled");
              break;
            }
          }
          if (success) {
            updateMessage(localizedSucessMessage);
            numberSucessful++;
            writeLogMessage(newFile,bw,localizedSucessMessage);
            addResultToList(newFile,success,localizedSucessMessage);
          }
 else {
            updateMessage(localizedErrorMessage);
            writeLogMessage(newFile,bw,localizedErrorMessage);
            addResultToList(newFile,success,localizedErrorMessage);
          }
        }
      }
    }
    updateMessage(Localization.lang("Finished copying"));
    String sucessMessage=Localization.lang("Copied %0 files of %1 sucessfully to %2",Integer.toString(numberSucessful),Integer.toString(totalFilesCounter),newPath.map(Path::getParent).map(Path::toString).orElse(""));
    updateMessage(sucessMessage);
    bw.write(sucessMessage);
    return results;
  }
 }
