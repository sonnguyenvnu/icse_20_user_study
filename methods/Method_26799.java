@GetMapping("formattedCollection") public String formattedCollection(@RequestParam @DateTimeFormat(iso=ISO.DATE) Collection<Date> values){
  return "Converted formatted collection " + values;
}
