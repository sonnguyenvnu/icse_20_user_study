private static void assertNT(boolean found){
  if (!found) {
    throw new IllegalArgumentException("Missing termination");
  }
}
