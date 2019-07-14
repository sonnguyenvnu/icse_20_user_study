/** 
 * Test for names derived from the swedish, dutch, or slavic that should get an alternate pronunciation of 'SV' to match the native version
 * @return true if swedish, dutch, or slavic derived name
 */
boolean Names_Beginning_With_SW_That_Get_Alt_SV(){
  if (StringAt(0,7,"SWANSON","SWENSON","SWINSON","SWENSEN","SWOBODA","") || StringAt(0,9,"SWIDERSKI","SWARTHOUT","") || StringAt(0,10,"SWEARENGIN","")) {
    return true;
  }
  return false;
}
