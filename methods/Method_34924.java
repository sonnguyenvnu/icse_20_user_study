/** 
 * Check if the specified path matches a robots.txt pattern
 * @param pattern The pattern to match
 * @param path The path to match with the pattern
 * @return True when the pattern matches, false if it does not
 */
public static boolean matchesRobotsPattern(String pattern,String path){
  return robotsPatternToRegexp(pattern).matcher(path).matches();
}
