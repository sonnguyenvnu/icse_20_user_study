private void assertExisted() throws FileNotFoundException {
  if (!exists()) {
    throw new FileNotFoundException("Bucket or OSSObject not existed.");
  }
}
