/** 
 * ?????????????
 * @param path
 * @return
 */
public static Map<String,String[]> loadCorpus(String path){
  Map<String,String[]> dataSet=new TreeMap<String,String[]>();
  File root=new File(path);
  File[] folders=root.listFiles();
  if (folders == null)   return null;
  for (  File folder : folders) {
    if (folder.isFile())     continue;
    File[] files=folder.listFiles();
    if (files == null)     continue;
    String[] documents=new String[files.length];
    for (int i=0; i < files.length; i++) {
      documents[i]=IOUtil.readTxt(files[i].getAbsolutePath());
    }
    dataSet.put(folder.getName(),documents);
  }
  return dataSet;
}
