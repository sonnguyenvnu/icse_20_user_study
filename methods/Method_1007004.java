@Override public FieldSet tokenize(String line){
  if (line.charAt(0) == 'F') {
    FieldSet fs=footerTokenizer.tokenize(line);
    long customerUpdateTotal=stepExecution.getReadCount();
    long fileUpdateTotal=fs.readLong(1);
    if (customerUpdateTotal != fileUpdateTotal) {
      throw new IllegalStateException("The total number of customer updates in the file footer does not match the " + "number entered  File footer total: [" + fileUpdateTotal + "] Total encountered during processing: [" + customerUpdateTotal + "]");
    }
 else {
      return null;
    }
  }
 else   if (line.charAt(0) == 'A' || line.charAt(0) == 'U' || line.charAt(0) == 'D') {
    return customerTokenizer.tokenize(line);
  }
 else {
    throw new IllegalArgumentException("Invalid line encountered for tokenizing: " + line);
  }
}
