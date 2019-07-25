@Override public void generate(List<TableMeta> tableMetas){
  System.out.println("Generate base model ...");
  System.out.println("Base Model Output Dir: " + baseModelOutputDir);
  Engine engine=Engine.create("forService");
  engine.setSourceFactory(new ClassPathSourceFactory());
  engine.addSharedMethod(new StrKit());
  engine.addSharedObject("getterTypeMap",getterTypeMap);
  engine.addSharedObject("javaKeyword",javaKeyword);
  for (  TableMeta tableMeta : tableMetas) {
    genBaseModelContent(tableMeta);
  }
  writeToFile(tableMetas);
}
