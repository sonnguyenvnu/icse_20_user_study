/** 
 * Converts bytecode-like description to java class name that can be loaded with a classloader. Uses less-known feature of class loaders for loading array classes.
 * @see #typedescToSignature(String,jodd.mutable.MutableInteger)
 */
public static String typedesc2ClassName(final String desc){
  String className=desc;
switch (desc.charAt(0)) {
case 'B':
case 'C':
case 'D':
case 'F':
case 'I':
case 'J':
case 'S':
case 'Z':
case 'V':
    if (desc.length() != 1) {
      throw new IllegalArgumentException(INVALID_BASE_TYPE + desc);
    }
  break;
case 'L':
className=className.substring(1,className.length() - 1);
break;
case '[':
className=className.replace('/','.');
break;
default :
throw new IllegalArgumentException(INVALID_TYPE_DESCRIPTION + desc);
}
return className;
}
