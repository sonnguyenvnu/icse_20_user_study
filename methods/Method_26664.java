@Override public Choice<Unifier> visitClassType(ClassType t,Unifier unifier){
  if (t instanceof IntersectionClassType) {
    IntersectionClassType intersection=(IntersectionClassType)t;
    return unifyList(unifier,bounds(),intersection.getComponents());
  }
  return Choice.none();
}
