String format(String str,String intlPrefix,String trunkPrefix,boolean prefixRequired){
  if (str.length() >= matchLen) {
    String begin=str.substring(0,matchLen);
    int val=0;
    Matcher matcher=pattern.matcher(begin);
    if (matcher.find()) {
      String num=matcher.group(0);
      val=Integer.parseInt(num);
    }
    for (    PhoneRule rule : rules) {
      if (val >= rule.minVal && val <= rule.maxVal && str.length() <= rule.maxLen) {
        if (prefixRequired) {
          if (((rule.flag12 & 0x03) == 0 && trunkPrefix == null && intlPrefix == null) || (trunkPrefix != null && (rule.flag12 & 0x01) != 0) || (intlPrefix != null && (rule.flag12 & 0x02) != 0)) {
            return rule.format(str,intlPrefix,trunkPrefix);
          }
        }
 else {
          if ((trunkPrefix == null && intlPrefix == null) || (trunkPrefix != null && (rule.flag12 & 0x01) != 0) || (intlPrefix != null && (rule.flag12 & 0x02) != 0)) {
            return rule.format(str,intlPrefix,trunkPrefix);
          }
        }
      }
    }
    if (!prefixRequired) {
      if (intlPrefix != null) {
        for (        PhoneRule rule : rules) {
          if (val >= rule.minVal && val <= rule.maxVal && str.length() <= rule.maxLen) {
            if (trunkPrefix == null || (rule.flag12 & 0x01) != 0) {
              return rule.format(str,intlPrefix,trunkPrefix);
            }
          }
        }
      }
 else       if (trunkPrefix != null) {
        for (        PhoneRule rule : rules) {
          if (val >= rule.minVal && val <= rule.maxVal && str.length() <= rule.maxLen) {
            if (intlPrefix == null || (rule.flag12 & 0x02) != 0) {
              return rule.format(str,intlPrefix,trunkPrefix);
            }
          }
        }
      }
    }
    return null;
  }
 else {
    return null;
  }
}
