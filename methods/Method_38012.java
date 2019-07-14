/** 
 * Surrounds the string with provided prefix and suffix if such missing from string.
 */
public static String surround(String string,final String prefix,final String suffix){
  if (!string.startsWith(prefix)) {
    string=prefix + string;
  }
  if (!string.endsWith(suffix)) {
    string+=suffix;
  }
  return string;
}
