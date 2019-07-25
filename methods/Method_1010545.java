public SNode convert(){
  if (mySource instanceof LanguageDescriptor) {
    myTarget=convertLanguage();
  }
 else   if (mySource instanceof SolutionDescriptor) {
    myTarget=convertSolution();
  }
 else   if (mySource instanceof DevkitDescriptor) {
    myTarget=convertDevkit();
  }
 else   if (mySource instanceof GeneratorDescriptor) {
    myTarget=convertGenerator();
  }
  return myTarget;
}
