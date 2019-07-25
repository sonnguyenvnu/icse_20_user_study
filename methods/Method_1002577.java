private static void help(){
  final HelpFormatter formatter=new HelpFormatter();
  formatter.printHelp(120,SchemaFormatTranslator.class.getSimpleName(),"[resolverPath] [sourceRoot] [destinationPath]",_options,"",true);
}
