/** 
 * Changes CamelCase string to lower case words separated by provided separator character. The following translations are applied: <ul> <li>Every upper case letter in the CamelCase name is translated into two characters, a separator and the lower case equivalent of the target character, with three exceptions. <ol><li>For contiguous sequences of upper case letters, characters after the first character are replaced only by their lower case equivalent, and are not preceded by a separator (<code>theFOO</code> to <code>the_foo</code>). <li>An upper case character in the first position of the CamelCase name is not preceded by a separator character, and is translated only to its lower case equivalent. (<code>Foo</code> to <code>foo</code> and not <code>_foo</code>) <li>An upper case character in the CamelCase name that is already preceded by a separator character is translated only to its lower case equivalent, and is not preceded by an additional separator. (<code>user_Name</code> to <code>user_name</code> and not <code>user__name</code>. </ol> <li>If the CamelCase name starts with a separator, then that separator is not included in the translated name, unless the CamelCase name is just one character in length, i.e., it is the separator character. This applies only to the first character of the CamelCase name. </ul>
 */
public static String fromCamelCase(final String input,final char separator){
  final int length=input.length();
  final StringBuilder result=new StringBuilder(length * 2);
  int resultLength=0;
  boolean prevTranslated=false;
  for (int i=0; i < length; i++) {
    char c=input.charAt(i);
    if (i > 0 || c != separator) {
      if (Character.isUpperCase(c)) {
        if (!prevTranslated && resultLength > 0 && result.charAt(resultLength - 1) != separator) {
          result.append(separator);
          resultLength++;
        }
        c=Character.toLowerCase(c);
        prevTranslated=true;
      }
 else {
        prevTranslated=false;
      }
      result.append(c);
      resultLength++;
    }
  }
  return resultLength > 0 ? result.toString() : input;
}
