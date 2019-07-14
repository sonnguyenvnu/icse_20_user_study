/** 
 * Writes the report to the output destination. 
 */
@Override public void print() throws IOException {
  results.sort(comparator());
  String report=assemble(results);
  String output=settings.report().output();
  if (output.equalsIgnoreCase("console")) {
    System.out.println(report);
  }
 else {
    Files.write(Paths.get(output),report.getBytes(UTF_8));
  }
}
