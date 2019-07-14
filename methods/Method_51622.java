private String glomName(boolean shortNames,String inputFileName,File file){
  if (shortNames) {
    if (inputFileName != null) {
      for (      final String prefix : inputFileName.split(",")) {
        final Path prefPath=Paths.get(prefix).toAbsolutePath();
        final String prefPathString=prefPath.toString();
        final String absoluteFilePath=file.getAbsolutePath();
        if (absoluteFilePath.startsWith(prefPathString)) {
          if (prefPath.toFile().isDirectory()) {
            return trimAnyPathSep(absoluteFilePath.substring(prefPathString.length()));
          }
 else {
            if (inputFileName.indexOf(FILE_SEPARATOR.charAt(0)) == -1) {
              return inputFileName;
            }
            return trimAnyPathSep(absoluteFilePath.substring(prefPathString.lastIndexOf(FILE_SEPARATOR)));
          }
        }
      }
    }
 else {
      return file.getName();
    }
  }
  try {
    return file.getCanonicalFile().getAbsolutePath();
  }
 catch (  Exception e) {
    return file.getAbsolutePath();
  }
}
