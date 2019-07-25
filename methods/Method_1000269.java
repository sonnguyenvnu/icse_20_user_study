public static boolean start(RootDoc root){
  ClassDoc[] classes=root.classes();
  for (int i=0; i < classes.length; ++i) {
    ClassDoc cd=classes[i];
    if (cd.typeName().toLowerCase().contains("test"))     continue;
    printMembers(cd.constructors());
    printMembers(cd.methods());
  }
  return true;
}
