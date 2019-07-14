public DestroyMethodPoint[] resolve(final Class<?> type){
  List<DestroyMethodPoint> list=new ArrayList<>();
  ClassDescriptor cd=new ClassDescriptor(type,false,false,false,null);
  MethodDescriptor[] allMethods=cd.getAllMethodDescriptors();
  for (  MethodDescriptor methodDescriptor : allMethods) {
    Method method=methodDescriptor.getMethod();
    PetiteDestroyMethod petiteDestroyMethod=method.getAnnotation(PetiteDestroyMethod.class);
    if (petiteDestroyMethod == null) {
      continue;
    }
    if (method.getParameterTypes().length > 0) {
      throw new PetiteException("Arguments are not allowed for Petite destroy method: " + type.getName() + '#' + method.getName());
    }
    list.add(new DestroyMethodPoint(method));
  }
  DestroyMethodPoint[] methods;
  if (list.isEmpty()) {
    methods=DestroyMethodPoint.EMPTY;
  }
 else {
    methods=list.toArray(new DestroyMethodPoint[0]);
  }
  return methods;
}
