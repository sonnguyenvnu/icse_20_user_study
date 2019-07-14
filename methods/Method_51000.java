private void mapLinesToOffsets() throws IOException {
  try (Scanner scanner=new Scanner(filePath)){
    int currentGlobalOffset=0;
    while (scanner.hasNextLine()) {
      lineToOffset.add(currentGlobalOffset);
      currentGlobalOffset+=getLineLengthWithLineSeparator(scanner);
    }
  }
 }
