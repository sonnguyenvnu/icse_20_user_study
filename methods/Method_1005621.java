/** 
 * Prints usages to out. Returns the number of matches found.
 */
public int grep(){
  for (  ClassDef classDef : dex.classDefs()) {
    currentClass=classDef;
    currentMethod=null;
    if (classDef.getClassDataOffset() == 0) {
      continue;
    }
    ClassData classData=dex.readClassData(classDef);
    int staticValuesOffset=classDef.getStaticValuesOffset();
    if (staticValuesOffset != 0) {
      readArray(new EncodedValueReader(dex.open(staticValuesOffset)));
    }
    for (    ClassData.Method method : classData.allMethods()) {
      currentMethod=method;
      if (method.getCodeOffset() != 0) {
        codeReader.visitAll(dex.readCode(method).getInstructions());
      }
    }
  }
  currentClass=null;
  currentMethod=null;
  return count;
}
