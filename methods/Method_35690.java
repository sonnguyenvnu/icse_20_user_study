private URI getZipEntryUri(final String name){
  final String lookFor=path + "/" + name;
  final Enumeration<? extends ZipEntry> enumeration=zipFile.entries();
  StringBuilder candidates=new StringBuilder();
  while (enumeration.hasMoreElements()) {
    final ZipEntry candidate=enumeration.nextElement();
    if (candidate.getName().equals(lookFor)) {
      return getUriFor(candidate);
    }
    candidates.append(candidate.getName() + "\n");
  }
  throw new RuntimeException("Was unable to find entry: \"" + lookFor + "\", found:\n" + candidates.toString());
}
