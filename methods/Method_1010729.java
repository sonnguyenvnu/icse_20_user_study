public void sort(List<SubstituteAction> substituteActions,String pattern){
  Comparator<SubstituteAction> comparator=(action1,action2) -> {
    if (action1 == action2) {
      return 0;
    }
    double priority1=getPriority(action1,pattern);
    double priority2=getPriority(action2,pattern);
    int compare=Double.compare(priority2,priority1);
    if (compare != 0) {
      return compare;
    }
    priority1=getLocalSortPriority(action1);
    priority2=getLocalSortPriority(action2);
    compare=Double.compare(priority1,priority2);
    if (compare != 0) {
      return compare;
    }
    String visibleMatchingText1=getVisibleMatchingText(action1,pattern);
    String visibleMatchingText2=getVisibleMatchingText(action2,pattern);
    boolean null_s1=(visibleMatchingText1 == null || visibleMatchingText1.length() == 0);
    boolean null_s2=(visibleMatchingText2 == null || visibleMatchingText2.length() == 0);
    if (null_s1) {
      return 1;
    }
    if (null_s2) {
      return -1;
    }
    return visibleMatchingText1.compareTo(visibleMatchingText2);
  }
;
  try {
    substituteActions.sort(comparator);
  }
 catch (  Throwable t) {
    LOG.error(t,t);
  }
}
