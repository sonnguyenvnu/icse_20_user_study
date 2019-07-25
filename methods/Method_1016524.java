public QualifiedName typename(){
  checkState(pkg != null,"No package statement");
  checkState(topLevelType != null,"No class declaration");
  return QualifiedName.of(pkg,topLevelType);
}
