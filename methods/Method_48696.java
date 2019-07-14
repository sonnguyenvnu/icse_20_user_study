public synchronized <V>void registerClassInternal(int registrationNo,Class<? extends V> datatype,AttributeSerializer<V> serializer){
  Preconditions.checkArgument(registrationNo > 0);
  Preconditions.checkNotNull(datatype);
  Preconditions.checkArgument(!handlers.containsKey(datatype),"DataType has already been registered: %s",datatype);
  Preconditions.checkArgument(!registrations.containsKey(registrationNo),"A datatype has already been registered for no: %s",registrationNo);
  Preconditions.checkNotNull(serializer,"Need to provide a serializer for datatype: %s",datatype);
  registrations.put(registrationNo,datatype);
  if (serializer instanceof SerializerInjected)   ((SerializerInjected)serializer).setSerializer(this);
  handlers.put(datatype,serializer);
}
