/** 
 * Unsafe version of  {@link #m_numDofs(int) m_numDofs}. 
 */
public static void nm_numDofs(long struct,int value){
  UNSAFE.putInt(null,struct + B3UserConstraintState.M_NUMDOFS,value);
}
