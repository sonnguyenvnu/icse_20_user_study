@GetMapping("/measures") public SizeReferences measures(){
  SizeReferences sizeReferences=new SizeReferences();
  sizeReferences.setMeasures(Arrays.asList(MeasureUnit.values()));
  sizeReferences.setWeights(Arrays.asList(WeightUnit.values()));
  return sizeReferences;
}
