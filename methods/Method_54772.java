/** 
 * Unsafe version of  {@link #m_numDofs}. 
 */
public static int nm_numDofs(long struct){
  return UNSAFE.getInt(null,struct + B3UserConstraintState.M_NUMDOFS);
}
