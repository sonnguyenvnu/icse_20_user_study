/** 
 * @param options can be one of "tsv", "csv", "bin", or "html"
 */
public boolean saveTable(Table table,String filename,String options){
  try {
    File outputFile=saveFile(filename);
    return table.save(outputFile,options);
  }
 catch (  IOException e) {
    printStackTrace(e);
    return false;
  }
}
