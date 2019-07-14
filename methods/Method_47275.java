public static boolean isStreamMedia(SmbFile file){
  return pattern.matcher(file.getName()).matches();
}
