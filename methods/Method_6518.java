private void generateThumb(int mediaType,File originalPath,ThumbGenerateInfo info){
  if (mediaType != FileLoader.MEDIA_DIR_IMAGE && mediaType != FileLoader.MEDIA_DIR_VIDEO && mediaType != FileLoader.MEDIA_DIR_DOCUMENT || originalPath == null || info == null) {
    return;
  }
  String name=FileLoader.getAttachFileName(info.parentDocument);
  ThumbGenerateTask task=thumbGenerateTasks.get(name);
  if (task == null) {
    task=new ThumbGenerateTask(mediaType,originalPath,info);
    thumbGeneratingQueue.postRunnable(task);
  }
}
