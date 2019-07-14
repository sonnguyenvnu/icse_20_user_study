/** 
 * ?????????????
 * @param folderPath
 * @return
 */
public static Map<String,String[]> loadCorpusWithException(String folderPath,String charsetName) throws IOException {
  if (folderPath == null)   throw new IllegalArgumentException("?? folderPath == null");
  File root=new File(folderPath);
  if (!root.exists())   throw new IllegalArgumentException(String.format("?? %s ???",root.getAbsolutePath()));
  if (!root.isDirectory())   throw new IllegalArgumentException(String.format("?? %s ??????",root.getAbsolutePath()));
  Map<String,String[]> dataSet=new TreeMap<String,String[]>();
  File[] folders=root.listFiles();
  if (folders == null)   return null;
  for (  File folder : folders) {
    if (folder.isFile())     continue;
    File[] files=folder.listFiles();
    if (files == null)     continue;
    String[] documents=new String[files.length];
    for (int i=0; i < files.length; i++) {
      documents[i]=readTxt(files[i],charsetName);
    }
    dataSet.put(folder.getName(),documents);
  }
  return dataSet;
}
