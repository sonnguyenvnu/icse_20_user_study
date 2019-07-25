public SNode execute(SModule module){
  for (  RefactoringPartImpl part : ListSequence.fromList(myParts)) {
    part.execute(module,mySession,myRunner,myRefactoringProcessor);
  }
  return null;
}
