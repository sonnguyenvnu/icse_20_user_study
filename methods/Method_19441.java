/** 
 * LengthFilter and AllCaps do not implement isEqual. Correct for the deficiency. 
 */
private static boolean equalInputFilters(List<InputFilter> a,List<InputFilter> b){
  if (a == null && b == null) {
    return true;
  }
  if (a == null || b == null) {
    return false;
  }
  if (a.size() != b.size()) {
    return false;
  }
  for (int i=0; i < a.size(); i++) {
    InputFilter fa=a.get(i);
    InputFilter fb=b.get(i);
    if (fa instanceof InputFilter.AllCaps && fb instanceof InputFilter.AllCaps) {
      continue;
    }
    if (SDK_INT >= LOLLIPOP) {
      if (fa instanceof InputFilter.LengthFilter && fb instanceof InputFilter.LengthFilter) {
        if (((InputFilter.LengthFilter)fa).getMax() != ((InputFilter.LengthFilter)fb).getMax()) {
          return false;
        }
        continue;
      }
    }
    if (!equals(fa,fb)) {
      return false;
    }
  }
  return true;
}
