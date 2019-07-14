/** 
 * ??????root
 * @return {@code true}: root<br> {@code false}: ?root
 */
public static boolean isRoot(){
  return execCmd("echo root",true,false).result == 0;
}
