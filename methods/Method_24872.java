public static List<Edit> wrapSketch(PdePreprocessor.Mode mode,String className,int sourceLength){
  List<Edit> edits=new ArrayList<>();
  StringBuilder b=new StringBuilder();
  if (mode != PdePreprocessor.Mode.JAVA) {
    b.append("\npublic class ").append(className).append(" extends PApplet {\n");
    if (mode == PdePreprocessor.Mode.STATIC) {
      b.append("public void setup() {\n");
    }
  }
  edits.add(Edit.insert(0,b.toString()));
  b.setLength(0);
  if (mode != PdePreprocessor.Mode.JAVA) {
    if (mode == PdePreprocessor.Mode.STATIC) {
      b.append("\n}");
    }
    b.append("\n}\n");
  }
  edits.add(Edit.insert(sourceLength,b.toString()));
  return edits;
}
