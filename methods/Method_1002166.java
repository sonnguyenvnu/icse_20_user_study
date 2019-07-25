public static ArrayList<String> list(File zipfile,String... matches){
  ArrayList<String> filelist=new ArrayList<>();
  try (FileInputStream in=new FileInputStream(zipfile);ZipInputStream zis=new ZipInputStream(in)){
    ZipEntry entry;
    while ((entry=zis.getNextEntry()) != null) {
      String name=entry.getName();
      for (      String match : matches) {
        if (name.contains(match)) {
          filelist.add(name);
          continue;
        }
      }
    }
    zis.closeEntry();
    return filelist;
  }
 catch (  IOException e) {
    e.printStackTrace();
    Log.e(TAG,"Compression.list: " + e.toString());
  }
  return null;
}
