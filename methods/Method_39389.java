public InitMethodPoint[] resolve(final Class<?> type){
  List<InitMethodPoint> list=new ArrayList<>();
  ClassDescriptor cd=new ClassDescriptor(type,false,false,false,null);
  MethodDescriptor[] allMethods=cd.getAllMethodDescriptors();
  for (  MethodDescriptor methodDescriptor : allMethods) {
    Method method=methodDescriptor.getMethod();
    PetiteInitMethod petiteInitMethod=method.getAnnotation(PetiteInitMethod.class);
    if (petiteInitMethod == null) {
      continue;
    }
    if (method.getParameterTypes().length > 0) {
      throw new PetiteException("Arguments are not allowed for Petite init method: " + type.getName() + '#' + method.getName());
    }
    int order=petiteInitMethod.order();
    list.add(new InitMethodPoint(method,order,petiteInitMethod.invoke()));
  }
  InitMethodPoint[] methods;
  if (list.isEmpty()) {
    methods=InitMethodPoint.EMPTY;
  }
 else {
    Collections.sort(list);
    methods=list.toArray(new InitMethodPoint[0]);
  }
  return methods;
}
