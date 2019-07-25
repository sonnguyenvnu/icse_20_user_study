@Override public Integer call() throws Exception {
  System.out.println("Hi!");
  System.out.println("x = " + x);
  System.out.println("from bundle: " + spec.usageMessage().messages().getString("a","not found"));
  System.out.println(UUID.randomUUID());
  return 0;
}
