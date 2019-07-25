public static List<CompilationUnit> build(CompilationUnitsAndTypeBindings compilationUnitsAndTypeBindings){
  Map<String,org.eclipse.jdt.core.dom.CompilationUnit> jdtUnitsByFilePath=compilationUnitsAndTypeBindings.getCompilationUnitsByFilePath();
  Iterable<ITypeBinding> wellKnownTypeBindings=compilationUnitsAndTypeBindings.getTypeBindings();
  CompilationUnitBuilder compilationUnitBuilder=new CompilationUnitBuilder();
  List<Entry<String,org.eclipse.jdt.core.dom.CompilationUnit>> entries=new ArrayList<>(jdtUnitsByFilePath.entrySet());
  sortPackageInfoFirst(entries);
  return entries.stream().map(entry -> compilationUnitBuilder.buildCompilationUnit(entry.getKey(),entry.getValue(),wellKnownTypeBindings)).collect(toImmutableList());
}
