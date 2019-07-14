private static void moveModuleInfo(Path moduleInfoClass) throws IOException {
  Path java9=moduleInfoClass.resolveSibling("META-INF/versions/9");
  Files.createDirectories(java9);
  Files.move(moduleInfoClass,java9.resolve("module-info.class"),StandardCopyOption.REPLACE_EXISTING);
}
