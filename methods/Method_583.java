private boolean checkTime(char h0,char h1,char m0,char m1,char s0,char s1){
  if (h0 == '0') {
    if (h1 < '0' || h1 > '9') {
      return false;
    }
  }
 else   if (h0 == '1') {
    if (h1 < '0' || h1 > '9') {
      return false;
    }
  }
 else   if (h0 == '2') {
    if (h1 < '0' || h1 > '4') {
      return false;
    }
  }
 else {
    return false;
  }
  if (m0 >= '0' && m0 <= '5') {
    if (m1 < '0' || m1 > '9') {
      return false;
    }
  }
 else   if (m0 == '6') {
    if (m1 != '0') {
      return false;
    }
  }
 else {
    return false;
  }
  if (s0 >= '0' && s0 <= '5') {
    if (s1 < '0' || s1 > '9') {
      return false;
    }
  }
 else   if (s0 == '6') {
    if (s1 != '0') {
      return false;
    }
  }
 else {
    return false;
  }
  return true;
}
