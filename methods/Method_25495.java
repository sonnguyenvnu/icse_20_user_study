private BugChecker instantiateChecker(BugCheckerInfo checker){
  @SuppressWarnings("unchecked") Optional<Constructor<BugChecker>> flagsConstructor=Arrays.stream((Constructor<BugChecker>[])checker.checkerClass().getConstructors()).filter(c -> Arrays.equals(c.getParameterTypes(),new Class<?>[]{ErrorProneFlags.class})).findFirst();
  if (flagsConstructor.isPresent()) {
    try {
      return flagsConstructor.get().newInstance(getFlags());
    }
 catch (    ReflectiveOperationException e) {
      throw new LinkageError("Could not instantiate BugChecker.",e);
    }
  }
  try {
    return checker.checkerClass().getConstructor().newInstance();
  }
 catch (  ReflectiveOperationException e) {
    throw new LinkageError("Could not instantiate BugChecker.",e);
  }
}
