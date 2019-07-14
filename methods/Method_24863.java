void rename(PreprocessedSketch ps,IBinding binding,String newName){
  CompilationUnit root=ps.compilationUnit;
  if (binding.getKind() == IBinding.METHOD) {
    IMethodBinding method=(IMethodBinding)binding;
    if (method.isConstructor()) {
      binding=method.getDeclaringClass();
    }
  }
  ASTNode decl=root.findDeclaringNode(binding.getKey());
  if (decl == null)   return;
  showUsage.hide();
  List<SimpleName> occurrences=new ArrayList<>();
  occurrences.addAll(findAllOccurrences(root,binding.getKey()));
  if (binding.getKind() == IBinding.TYPE) {
    ITypeBinding type=(ITypeBinding)binding;
    IMethodBinding[] methods=type.getDeclaredMethods();
    Arrays.stream(methods).filter(IMethodBinding::isConstructor).flatMap(c -> findAllOccurrences(root,c.getKey()).stream()).forEach(occurrences::add);
  }
  Map<Integer,List<SketchInterval>> mappedNodes=occurrences.stream().map(ps::mapJavaToSketch).filter(ps::inRange).collect(Collectors.groupingBy(interval -> interval.tabIndex));
  Sketch sketch=ps.sketch;
  editor.startCompoundEdit();
  mappedNodes.entrySet().forEach(entry -> {
    int tabIndex=entry.getKey();
    SketchCode sketchCode=sketch.getCode(tabIndex);
    SyntaxDocument document=(SyntaxDocument)sketchCode.getDocument();
    List<SketchInterval> nodes=entry.getValue();
    nodes.stream().sorted(Comparator.comparing((    SketchInterval si) -> si.startTabOffset).reversed()).forEach(si -> {
      int documentLength=document.getLength();
      if (si.startTabOffset >= 0 && si.startTabOffset <= documentLength && si.stopTabOffset >= 0 && si.stopTabOffset <= documentLength) {
        int length=si.stopTabOffset - si.startTabOffset;
        try {
          document.remove(si.startTabOffset,length);
          document.insertString(si.startTabOffset,newName,null);
        }
 catch (        BadLocationException e) {
        }
      }
    }
);
    try {
      sketchCode.setProgram(document.getText(0,document.getLength()));
    }
 catch (    BadLocationException e) {
    }
    sketchCode.setModified(true);
  }
);
  editor.stopCompoundEdit();
  editor.repaintHeader();
  int currentTabIndex=sketch.getCurrentCodeIndex();
  final int currentOffset=editor.getCaretOffset();
  int precedingIntervals=(int)mappedNodes.getOrDefault(currentTabIndex,Collections.emptyList()).stream().filter(interval -> interval.stopTabOffset < currentOffset).count();
  int intervalLengthDiff=newName.length() - binding.getName().length();
  int offsetDiff=precedingIntervals * intervalLengthDiff;
  editor.getTextArea().setCaretPosition(currentOffset + offsetDiff);
}
