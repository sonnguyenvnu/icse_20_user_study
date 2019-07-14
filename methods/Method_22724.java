/** 
 * Produce a sanitized name that fits our standards for likely to work. <p/> Java classes have a wider range of names that are technically allowed (supposedly any Unicode name) than what we support. The reason for going more narrow is to avoid situations with text encodings and converting during the process of moving files between operating systems, i.e. uploading from a Windows machine to a Linux server, or reading a FAT32 partition in OS X and using a thumb drive. <p/> This helper function replaces everything but A-Z, a-z, and 0-9 with underscores. Also disallows starting the sketch name with a digit or underscore. <p/> In Processing 2.0, sketches can no longer begin with an underscore, because these aren't valid class names on Android.
 */
static public String sanitizeName(String origName){
  char orig[]=origName.toCharArray();
  StringBuilder sb=new StringBuilder();
  if (!asciiLetter(orig[0])) {
    sb.append("sketch_");
  }
  for (  char c : orig) {
    if (asciiLetter(c) || (c >= '0' && c <= '9')) {
      sb.append(c);
    }
 else {
      sb.append('_');
    }
  }
  if (sb.length() > 63) {
    sb.setLength(63);
  }
  int underscore=0;
  while (underscore < sb.length() && sb.charAt(underscore) == '_') {
    underscore++;
  }
  if (underscore == sb.length()) {
    return "bad_sketch_name_please_fix";
  }
 else   if (underscore != 0) {
    return sb.substring(underscore);
  }
  return sb.toString();
}
