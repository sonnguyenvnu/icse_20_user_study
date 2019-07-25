/** 
 * <p>Removes  {@code separator} from the end of{@code str} if it's there, otherwise leave it alone.</p><p>NOTE: This method changed in version 2.0. It now more closely matches Perl chomp. For the previous behavior, use  {@link #substringBeforeLast(String,String)}. This method uses  {@link String#endsWith(String)}.</p> <pre> StringUtils.chomp(null, *)         = null StringUtils.chomp("", *)           = "" StringUtils.chomp("foobar", "bar") = "foo" StringUtils.chomp("foobar", "baz") = "foobar" StringUtils.chomp("foo", "foo")    = "" StringUtils.chomp("foo ", "foo")   = "foo " StringUtils.chomp(" foo", "foo")   = " " StringUtils.chomp("foo", "foooo")  = "foo" StringUtils.chomp("foo", "")       = "foo" StringUtils.chomp("foo", null)     = "foo" </pre>
 * @param str  the String to chomp from, may be null
 * @param separator  separator String, may be null
 * @return String without trailing separator, {@code null} if null String input
 * @deprecated This feature will be removed in Lang 4.0, use {@link StringUtils#removeEnd(String,String)} instead
 */
@Deprecated public static String chomp(String str,String separator){
  return removeEnd(str,separator);
}
