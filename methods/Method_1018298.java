public static void hello(){
  System.out.println("Hello, I'm a sample class exported by ark-plugin.");
  System.out.println(String.format("I'm loaded by %s",SampleClassExported.class.getClassLoader()));
}
