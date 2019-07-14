protected void searchJarAndMetaInf(List<URL> urls,File directory) throws Exception {
  File metaInf=new File(directory,"META-INF");
  if (metaInf.exists() && metaInf.isDirectory()) {
    urls.add(directory.toURI().toURL());
  }
 else {
    for (    File child : directory.listFiles()) {
      if (child.isDirectory()) {
        searchJarAndMetaInf(urls,child);
      }
 else       if (child.getName().toLowerCase().endsWith(".jar")) {
        urls.add(new URL("jar","",child.toURI().toURL().toString() + "!/"));
      }
    }
  }
}
