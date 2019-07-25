@SuppressFBWarnings("NP_NULL_ON_SOME_PATH_FROM_RETURN_VALUE") static void check(SpotlessTask task,Formatter formatter,List<File> problemFiles) throws IOException {
  if (problemFiles.isEmpty()) {
    task.getLogger().info(StringPrinter.buildStringFromLines(task.getName() + " is in paddedCell() mode, but it doesn't need to be.","If you remove that option, spotless will run ~2x faster.","For details see " + URL));
  }
  File diagnoseDir=diagnoseDir(task);
  File rootDir=task.getProject().getRootDir();
  List<File> stillFailing=PaddedCellBulk.check(rootDir,diagnoseDir,formatter,problemFiles);
  if (!stillFailing.isEmpty()) {
    throw task.formatViolationsFor(formatter,problemFiles);
  }
}
