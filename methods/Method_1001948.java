public static void greedy(Cookie cookie,LongestMatchCache lmc,byte[] input,int from,int to,LzStore store){
  Hash h=cookie.h;
  h.init(input,Math.max(from - 0x8000,0),from,to);
  int prevLength=0;
  int prevMatch=0;
  char[] dummySubLen=cookie.c259a;
  boolean matchAvailable=false;
  for (int i=from; i < to; i++) {
    h.updateHash(input,i,to);
    findLongestMatch(cookie,lmc,from,h,input,i,to,258,dummySubLen);
    int len=cookie.lenVal;
    int dist=cookie.distVal;
    int lengthScore=dist > 1024 ? len - 1 : len;
    int prevLengthScore=prevMatch > 1024 ? prevLength - 1 : prevLength;
    if (matchAvailable) {
      matchAvailable=false;
      if (lengthScore > prevLengthScore + 1) {
        store.append((char)(input[i - 1] & 0xFF),(char)0);
        if (lengthScore >= 3 && len < 258) {
          matchAvailable=true;
          prevLength=len;
          prevMatch=dist;
          continue;
        }
      }
 else {
        store.append((char)prevLength,(char)prevMatch);
        for (int j=2; j < prevLength; j++) {
          i++;
          h.updateHash(input,i,to);
        }
        continue;
      }
    }
 else     if (lengthScore >= 3 && len < 258) {
      matchAvailable=true;
      prevLength=len;
      prevMatch=dist;
      continue;
    }
    if (lengthScore >= 3) {
      store.append((char)len,(char)dist);
    }
 else {
      len=1;
      store.append((char)(input[i] & 0xFF),(char)0);
    }
    for (int j=1; j < len; j++) {
      i++;
      h.updateHash(input,i,to);
    }
  }
}
