/** 
 * Test for names derived from the german that should get an alternate pronunciation of 'XV' to match the german version spelled "schw-"
 * @return true if german derived name
 */
boolean Names_Beginning_With_SW_That_Get_Alt_XV(){
  if (StringAt(0,5,"SWART","") || StringAt(0,6,"SWARTZ","SWARTS","SWIGER","") || StringAt(0,7,"SWITZER","SWANGER","SWIGERT","SWIGART","SWIHART","") || StringAt(0,8,"SWEITZER","SWATZELL","SWINDLER","") || StringAt(0,9,"SWINEHART","") || StringAt(0,10,"SWEARINGEN","")) {
    return true;
  }
  return false;
}
