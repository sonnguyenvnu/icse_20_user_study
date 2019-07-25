public Instance pipe(Instance carrier){
  try {
    if (carrier.getData() instanceof File)     carrier.setData(pipe((File)carrier.getData()));
 else     if (carrier.getData() instanceof BufferedReader)     carrier.setData(pipe((BufferedReader)carrier.getData()));
 else     throw new IllegalArgumentException("Doesn't handle class " + carrier.getClass());
  }
 catch (  IOException e) {
    throw new IllegalArgumentException("IOException");
  }
  return carrier;
}
