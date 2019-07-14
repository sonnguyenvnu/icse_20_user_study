private boolean isUsefulLine(String line){
  for (  String usefulLine : MIGRATE_SAMPLE_USEFUL_LINES) {
    if (line.contains(usefulLine)) {
      return true;
    }
  }
  return false;
}
