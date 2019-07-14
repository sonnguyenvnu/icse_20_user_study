/** 
 * Quickly read small files?no more than 10,000 lines.
 * @param in    the POI filesystem that contains the Workbook stream.
 * @param sheet read sheet.
 * @return analysis result.
 */
public static List<Object> read(InputStream in,Sheet sheet){
  final List<Object> rows=new ArrayList<Object>();
  new ExcelReader(in,null,new AnalysisEventListener(){
    @Override public void invoke(    Object object,    AnalysisContext context){
      rows.add(object);
    }
    @Override public void doAfterAllAnalysed(    AnalysisContext context){
    }
  }
,false).read(sheet);
  return rows;
}
