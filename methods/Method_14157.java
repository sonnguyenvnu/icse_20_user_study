/** 
 * Test if 'B' is pronounced in these "-MB-" contexts
 * @return true if "-B-" is pronounced in these contexts
 */
boolean Test_Pronounced_MB_2(){
  if (StringAt((m_current - 1),5,"OMBAS","OMBAD","UMBRA","") || StringAt((m_current - 3),4,"FLAM","")) {
    return true;
  }
  return false;
}
