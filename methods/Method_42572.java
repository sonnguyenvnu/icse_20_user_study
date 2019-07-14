/** 
 * ??csv?? ???list? ????????String???????????list? ???????????list?
 * @param file ????cvs??
 * @param charsetName ???????
 * @return
 * @throws IOException
 */
public static List<List<String>> readCSVFile(String file,String charsetName) throws IOException {
  if (file == null || !file.contains(".csv")) {
    return null;
  }
  InputStreamReader fr=new InputStreamReader(new FileInputStream(file),charsetName);
  BufferedReader br=new BufferedReader(fr);
  String rec=null;
  String str;
  List<List<String>> listFile=new ArrayList<List<String>>();
  try {
    while ((rec=br.readLine()) != null) {
      Pattern pCells=Pattern.compile("(\"[^\"]*(\"{2})*[^\"]*\")*[^,]*,");
      Matcher mCells=pCells.matcher(rec);
      List<String> cells=new ArrayList<String>();
      while (mCells.find()) {
        str=mCells.group();
        str=str.replaceAll("(?sm)\"?([^\"]*(\"{2})*[^\"]*)\"?.*,","$1");
        str=str.replaceAll("(?sm)(\"(\"))","$2");
        cells.add(str);
      }
      listFile.add(cells);
    }
  }
 catch (  Exception e) {
    LOG.error("??",e);
  }
 finally {
    if (fr != null) {
      fr.close();
    }
    if (br != null) {
      br.close();
    }
  }
  return listFile;
}
