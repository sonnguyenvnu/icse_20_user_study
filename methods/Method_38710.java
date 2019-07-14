/** 
 * Returns <code>true</code> if  {@link #pushName(String,boolean)}  pushed name}has been  {@link #popName() poped, i.e. used}.
 */
public boolean isNamePopped(){
  boolean b=!isPushed;
  isPushed=false;
  return b;
}
