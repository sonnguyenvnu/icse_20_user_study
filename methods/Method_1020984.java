public void generate(List<TableMeta> tableMetas){
  System.out.println("Generate Service Impl ...");
  System.out.println("Service Impl Output Dir: " + buildOutPutDir());
  Engine engine=Engine.create("forServiceImpl");
  engine.setSourceFactory(new ClassPathSourceFactory());
  engine.addSharedMethod(new StrKit());
  engine.addSharedObject("getterTypeMap",getterTypeMap);
  engine.addSharedObject("javaKeyword",JavaKeyword.me);
  for (  TableMeta tableMeta : tableMetas) {
    genBaseModelContent(tableMeta);
  }
  writeToFile(tableMetas);
}
