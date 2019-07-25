public LocalService read(Class<?> clazz) throws LocalServiceBindingException {
  log.fine("Reading and binding annotations of service implementation class: " + clazz);
  if (clazz.isAnnotationPresent(UpnpService.class)) {
    UpnpService annotation=clazz.getAnnotation(UpnpService.class);
    UpnpServiceId idAnnotation=annotation.serviceId();
    UpnpServiceType typeAnnotation=annotation.serviceType();
    ServiceId serviceId=idAnnotation.namespace().equals(UDAServiceId.DEFAULT_NAMESPACE) ? new UDAServiceId(idAnnotation.value()) : new ServiceId(idAnnotation.namespace(),idAnnotation.value());
    ServiceType serviceType=typeAnnotation.namespace().equals(UDAServiceType.DEFAULT_NAMESPACE) ? new UDAServiceType(typeAnnotation.value(),typeAnnotation.version()) : new ServiceType(typeAnnotation.namespace(),typeAnnotation.value(),typeAnnotation.version());
    boolean supportsQueryStateVariables=annotation.supportsQueryStateVariables();
    Set<Class> stringConvertibleTypes=readStringConvertibleTypes(annotation.stringConvertibleTypes());
    return read(clazz,serviceId,serviceType,supportsQueryStateVariables,stringConvertibleTypes);
  }
 else {
    throw new LocalServiceBindingException("Given class is not an @UpnpService");
  }
}
