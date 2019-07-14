/** 
 * Stores signatures for all super public methods not already overridden by target class. All this methods will be accepted for proxyfication.
 */
@Override public void visitEnd(){
  if (classAnnotations != null) {
    annotations=classAnnotations.toArray(new AnnotationInfo[0]);
    classAnnotations=null;
  }
  final List<String> superList=new ArrayList<>();
  final Set<String> allInterfaces=new HashSet<>();
  if (nextInterfaces != null) {
    allInterfaces.addAll(nextInterfaces);
  }
  while (nextSupername != null) {
    InputStream inputStream=null;
    ClassReader cr;
    try {
      inputStream=ClassLoaderUtil.getClassAsStream(nextSupername,classLoader);
      cr=new ClassReader(inputStream);
    }
 catch (    IOException ioex) {
      throw new ProxettaException("Unable to inspect super class: " + nextSupername,ioex);
    }
 finally {
      StreamUtil.close(inputStream);
    }
    superList.add(nextSupername);
    superClassReaders.add(cr);
    cr.accept(new SuperClassVisitor(this),0);
    if (cr.getInterfaces() != null) {
      Collections.addAll(allInterfaces,cr.getInterfaces());
    }
  }
  superClasses=superList.toArray(new String[0]);
  Set<String> todoInterfaces=new HashSet<>(allInterfaces);
  Set<String> newCollectedInterfaces=new HashSet<>();
  while (true) {
    for (    String next : todoInterfaces) {
      InputStream inputStream=null;
      ClassReader cr;
      try {
        inputStream=ClassLoaderUtil.getClassAsStream(next,classLoader);
        cr=new ClassReader(inputStream);
      }
 catch (      IOException ioex) {
        throw new ProxettaException("Unable to inspect super interface: " + next,ioex);
      }
 finally {
        StreamUtil.close(inputStream);
      }
      superClassReaders.add(cr);
      cr.accept(new SuperClassVisitor(this),0);
      if (cr.getInterfaces() != null) {
        for (        String newInterface : cr.getInterfaces()) {
          if (!allInterfaces.contains(newInterface) && !todoInterfaces.contains(newInterface)) {
            newCollectedInterfaces.add(newInterface);
          }
        }
      }
    }
    allInterfaces.addAll(todoInterfaces);
    if (newCollectedInterfaces.isEmpty()) {
      break;
    }
    todoInterfaces.clear();
    todoInterfaces.addAll(newCollectedInterfaces);
    newCollectedInterfaces.clear();
  }
}
