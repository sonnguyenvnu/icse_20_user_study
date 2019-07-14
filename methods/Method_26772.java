private static void findExamples(List<Path> examples,Path dir,DirectoryStream.Filter<Path> filter) throws IOException {
  try (DirectoryStream<Path> stream=Files.newDirectoryStream(dir,filter)){
    for (    Path entry : stream) {
      if (Files.isDirectory(entry)) {
        findExamples(examples,entry,filter);
      }
 else {
        examples.add(entry);
      }
    }
  }
 }
