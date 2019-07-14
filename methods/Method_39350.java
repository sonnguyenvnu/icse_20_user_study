public ValueInjectionPoint[] resolveParamInjectionPoints(final Class type){
  final ClassDescriptor cd=ClassIntrospector.get().lookup(type);
  final List<ValueInjectionPoint> valueInjectionPointList=new ArrayList<>();
  for (  final PropertyDescriptor pd : cd.getAllPropertyDescriptors()) {
    final FieldDescriptor fd=pd.getFieldDescriptor();
    if (fd != null) {
      final PetiteValue petiteValue=fd.getField().getAnnotation(PetiteValue.class);
      if (petiteValue != null) {
        valueInjectionPointList.add(new ValueInjectionPoint(pd.getName(),petiteValue.value()));
        continue;
      }
    }
    MethodDescriptor md=pd.getWriteMethodDescriptor();
    if (md != null) {
      final PetiteValue petiteValue=md.getMethod().getAnnotation(PetiteValue.class);
      if (petiteValue != null) {
        valueInjectionPointList.add(new ValueInjectionPoint(pd.getName(),petiteValue.value()));
        continue;
      }
    }
  }
  return valueInjectionPointList.toArray(new ValueInjectionPoint[0]);
}
