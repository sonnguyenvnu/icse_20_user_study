private static void manifest(String path){
  String manifest=new String(readFile(new File(path)),StandardCharsets.UTF_8);
  manifest=replaceAll(manifest,"${version}",getVersion());
  manifest=replaceAll(manifest,"${buildJdk}",getJavaSpecVersion());
  String createdBy=System.getProperty("java.runtime.version") + " (" + System.getProperty("java.vm.vendor") + ")";
  manifest=replaceAll(manifest,"${createdBy}",createdBy);
  mkdir("temp/META-INF");
  writeFile(new File("temp/META-INF/MANIFEST.MF"),manifest.getBytes());
}
