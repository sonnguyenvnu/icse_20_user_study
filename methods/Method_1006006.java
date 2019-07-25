public BibDatabase perform(){
  BibDatabase subDatabase=null;
  if (!auxFile.isEmpty() && (database != null)) {
    AuxParser auxParser=new DefaultAuxParser(database);
    AuxParserResult result=auxParser.parse(Paths.get(auxFile));
    subDatabase=result.getGeneratedBibDatabase();
    System.out.println(new AuxParserResultViewModel(result).getInformation(true));
  }
  return subDatabase;
}
