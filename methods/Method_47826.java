private String writeZipFile() throws IOException {
  SimpleDateFormat dateFormat=DateFormats.getCSVDateFormat();
  String date=dateFormat.format(DateUtils.getStartOfToday());
  String zipFilename=String.format("%s/Loop Habits CSV %s.zip",exportDirName,date);
  FileOutputStream fos=new FileOutputStream(zipFilename);
  ZipOutputStream zos=new ZipOutputStream(fos);
  for (  String filename : generateFilenames)   addFileToZip(zos,filename);
  zos.close();
  fos.close();
  return zipFilename;
}
