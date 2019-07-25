private byte[] serialize(Object object){
  return (byte[])this.conversionService.convert(object,TypeDescriptor.valueOf(Object.class),TypeDescriptor.valueOf(byte[].class));
}
