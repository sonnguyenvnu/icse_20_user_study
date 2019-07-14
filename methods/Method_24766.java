public static ArrayList<CompletionCandidate> getMembersForType(PreprocessedSketch ps,ClassMember tehClass,String childToLookFor,boolean noCompare,boolean staticOnly){
  String child=childToLookFor.toLowerCase();
  ArrayList<CompletionCandidate> candidates=new ArrayList<>();
  log("getMemFoType-> Looking for match " + child + " inside " + tehClass + " noCompare " + noCompare + " staticOnly " + staticOnly);
  if (tehClass == null) {
    return candidates;
  }
  if (tehClass.getDeclaringNode() instanceof TypeDeclaration) {
    TypeDeclaration td=(TypeDeclaration)tehClass.getDeclaringNode();
{
      FieldDeclaration[] fields=td.getFields();
      for (      FieldDeclaration field : fields) {
        if (staticOnly && !isStatic(field.modifiers())) {
          continue;
        }
        List<VariableDeclarationFragment> vdfs=field.fragments();
        for (        VariableDeclarationFragment vdf : vdfs) {
          if (noCompare) {
            candidates.add(new CompletionCandidate(vdf));
          }
 else           if (vdf.getName().toString().toLowerCase().startsWith(child))           candidates.add(new CompletionCandidate(vdf));
        }
      }
    }
{
      MethodDeclaration[] methods=td.getMethods();
      for (      MethodDeclaration method : methods) {
        if (staticOnly && !isStatic(method.modifiers())) {
          continue;
        }
        if (noCompare) {
          candidates.add(new CompletionCandidate(method));
        }
 else         if (method.getName().toString().toLowerCase().startsWith(child))         candidates.add(new CompletionCandidate(method));
      }
    }
    ArrayList<CompletionCandidate> superClassCandidates;
    if (td.getSuperclassType() != null) {
      log(getNodeAsString(td.getSuperclassType()) + " <-Looking into superclass of " + tehClass);
      superClassCandidates=getMembersForType(ps,new ClassMember(ps,td.getSuperclassType()),childToLookFor,noCompare,staticOnly);
    }
 else {
      superClassCandidates=getMembersForType(ps,new ClassMember(Object.class),childToLookFor,noCompare,staticOnly);
    }
    for (    CompletionCandidate cc : superClassCandidates) {
      candidates.add(cc);
    }
    return candidates;
  }
  Class<?> probableClass;
  if (tehClass.getClass_() != null) {
    probableClass=tehClass.getClass_();
  }
 else {
    probableClass=findClassIfExists(ps,tehClass.getTypeAsString());
    if (probableClass == null) {
      log("Couldn't find class " + tehClass.getTypeAsString());
      return candidates;
    }
    log("Loaded " + probableClass.toString());
  }
  for (  Method method : probableClass.getMethods()) {
    if (!Modifier.isStatic(method.getModifiers()) && staticOnly) {
      continue;
    }
    StringBuilder label=new StringBuilder(method.getName() + "(");
    for (int i=0; i < method.getParameterTypes().length; i++) {
      label.append(method.getParameterTypes()[i].getSimpleName());
      if (i < method.getParameterTypes().length - 1)       label.append(",");
    }
    label.append(")");
    if (noCompare) {
      candidates.add(new CompletionCandidate(method));
    }
 else     if (label.toString().toLowerCase().startsWith(child)) {
      candidates.add(new CompletionCandidate(method));
    }
  }
  for (  Field field : probableClass.getFields()) {
    if (!Modifier.isStatic(field.getModifiers()) && staticOnly) {
      continue;
    }
    if (noCompare) {
      candidates.add(new CompletionCandidate(field));
    }
 else     if (field.getName().toLowerCase().startsWith(child)) {
      candidates.add(new CompletionCandidate(field));
    }
  }
  if (probableClass.isArray() && !staticOnly) {
    String className=probableClass.getSimpleName();
    if (noCompare || "clone()".startsWith(child)) {
      String methodLabel="<html>clone() : " + className + " - <font color=#777777>" + className + "</font></html>";
      candidates.add(new CompletionCandidate("clone()",methodLabel,"clone()",CompletionCandidate.PREDEF_METHOD));
    }
    if ("length".startsWith(child)) {
      String fieldLabel="<html>length : int - <font color=#777777>" + className + "</font></html>";
      candidates.add(new CompletionCandidate("length",fieldLabel,"length",CompletionCandidate.PREDEF_FIELD));
    }
  }
  return candidates;
}
