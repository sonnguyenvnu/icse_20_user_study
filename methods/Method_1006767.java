public void parse() throws Exception {
  File folder=new File("/");
  if (folder.listFiles() == null) {
    return;
  }
  HashMap<String,File> files=new HashMap<String,File>();
  for (  File file : folder.listFiles()) {
    Long lastModifyTime=lastModefyTimeMap.get(file.getName());
    if (lastModifyTime == null || file.lastModified() > lastModifyTime) {
      lastModefyTimeMap.put(file.getName(),file.lastModified());
      files.put(file.getName(),file);
    }
  }
  for (  String fileName : fileNames) {
    File file=files.get(fileName);
    if (file == null) {
      continue;
    }
    if (fileName.contains(".xls")) {
      Map<String,Class<? extends ITemplateable>> sheetClassMap=fileSheetClassMap.get(fileName);
      if (sheetClassMap == null) {
        try {
          System.err.println("???? (config/excel.xml) ??? (" + fileName + ") ?????");
        }
 catch (        Exception e) {
          e.printStackTrace();
        }
      }
      Workbook workbook=null;
      try {
        workbook=Workbook.getWorkbook(file);
      }
 catch (      Exception e) {
        System.out.println("EXCEL?? (" + fileName + ") ????");
        e.printStackTrace();
        continue;
      }
      Sheet[] sheets=workbook.getSheets();
      Map<String,Sheet> sheetMap=new HashMap<String,Sheet>();
      for (      Sheet sheet : sheets) {
        sheetMap.put(sheet.getName(),sheet);
      }
      List<String> sheetNames=fileSheetNameClassMap.get(fileName);
      for (      String sheetName : sheetNames) {
        Sheet sheet=sheetMap.get(sheetName);
        if (sheet == null) {
          continue;
        }
        Class<? extends ITemplateable> clz=sheetClassMap.get(sheetName);
        if (clz == null) {
          try {
            System.err.println("???? (config/excel.xml) ??? (" + fileName + ") ????  (" + sheetName + ")?????");
          }
 catch (          Exception e) {
            e.printStackTrace();
          }
          continue;
        }
        Map<Object,ITemplateable> beans=new HashMap<Object,ITemplateable>();
        int rows=sheet.getRows();
        int startLine=2;
        Cell[] rowNames=sheet.getRow(1);
        Map<String,Integer> nameMap=new HashMap<String,Integer>();
        int i=0;
        for (        Cell cell : rowNames) {
          String name=cell.getContents().trim();
          if (name != null && !name.equals("")) {
            nameMap.put(name,i);
          }
          i++;
        }
        for (i=startLine; i < rows; i++) {
          Cell[] rowArr=sheet.getRow(i);
          ITemplateable bean=clz.newInstance();
          parse(bean,rowArr,nameMap);
          System.out.println(bean);
          beans.put(bean.getTemplateId(),bean);
        }
        System.out.println("Excel, clz = " + clz + ", beans = " + beans);
        Templates.put(clz,beans);
        Cell[] nameCells=sheet.getRow(0);
        Cell[] propertyCells=sheet.getRow(1);
        List<KV> schemaList=new ArrayList<>();
        int length=nameCells.length;
        for (int j=0; j < length; j++) {
          String k=propertyCells[j].getContents().trim();
          String v=nameCells[j].getContents().trim();
          KV kv=new KV(k,v);
          schemaList.add(kv);
        }
        Templates.put(clz,schemaList);
      }
    }
    System.out.println("-----------");
  }
}
