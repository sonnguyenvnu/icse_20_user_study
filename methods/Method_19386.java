private static void assertAPI11orHigher(){
  if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
    throw new RuntimeException("Spinner requires API 11 (HONEYCOMB) or greater");
  }
}
