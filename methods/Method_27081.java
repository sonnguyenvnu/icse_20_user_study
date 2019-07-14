public long getSize(){
  List<FilesListModel> models=getFilesAsList();
  if (!models.isEmpty()) {
    return Stream.of(models).flatMapToLong(filesListModel -> LongStream.of(filesListModel.getSize())).sum();
  }
  return 0;
}
