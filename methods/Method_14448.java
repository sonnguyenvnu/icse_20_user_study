public static void save(ProjectMetadata projectMeta,File projectDir) throws IOException {
  File tempFile=new File(projectDir,"metadata.temp.json");
  saveToFile(projectMeta,tempFile);
  File file=new File(projectDir,"metadata.json");
  File oldFile=new File(projectDir,"metadata.old.json");
  if (oldFile.exists()) {
    oldFile.delete();
  }
  if (file.exists()) {
    file.renameTo(oldFile);
  }
  tempFile.renameTo(file);
}
