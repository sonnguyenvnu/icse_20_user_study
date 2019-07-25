public static MDADiagram create(String uml){
  List<BlockUml> blocks=new SourceStringReader(uml).getBlocks();
  if (blocks.size() == 0) {
    uml="@startuml\n" + uml + "\n@enduml";
    blocks=new SourceStringReader(uml).getBlocks();
    if (blocks.size() == 0) {
      return null;
    }
  }
  final BlockUml block=blocks.get(0);
  final Diagram diagram=block.getDiagram();
  if (diagram instanceof ClassDiagram) {
    return new MDADiagramImpl((ClassDiagram)diagram);
  }
  return null;
}
