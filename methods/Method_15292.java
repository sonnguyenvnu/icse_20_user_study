/** 
 * ????
 */
public static void restoreDefault(){
  for (int i=0; i < KEYS.length; i++) {
    putBoolean(KEYS[i],defaultValues[i]);
  }
  init(context);
}
