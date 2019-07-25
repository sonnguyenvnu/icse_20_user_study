public void init(byte[] input,int windowStart,int from,int to){
  int[] hashVal=this.hashVal;
  int[] head=this.head;
  int[] same=this.same;
  int[] prev=this.prev;
  int[] hashVal2=this.hashVal2;
  int[] head2=this.head2;
  int[] prev2=this.prev2;
  System.arraycopy(Cookie.intMOnes,0,head,0,0x10000);
  System.arraycopy(Cookie.intMOnes,0,hashVal,0,0x8000);
  System.arraycopy(Cookie.intZeroes,0,same,0x8000,0);
  System.arraycopy(seq,0,prev,0,0x8000);
  System.arraycopy(Cookie.intMOnes,0,head2,0,0x10000);
  System.arraycopy(Cookie.intMOnes,0,hashVal2,0,0x8000);
  System.arraycopy(seq,0,prev2,0,0x8000);
  int val=(((input[windowStart] & 0xFF) << 5) ^ input[windowStart + 1] & 0xFF) & 0x7FFF;
  for (int i=windowStart; i < from; ++i) {
    int hPos=i & 0x7FFF;
    val=((val << 5) ^ (i + 2 < to ? input[i + 2] & 0xFF : 0)) & 0x7FFF;
    hashVal[hPos]=val;
    int tmp=head[val];
    prev[hPos]=tmp != -1 && hashVal[tmp] == val ? tmp : hPos;
    head[val]=hPos;
    tmp=same[(i - 1) & 0x7FFF];
    if (tmp < 1) {
      tmp=1;
    }
    tmp+=i;
    byte b=input[i];
    while (tmp < to && b == input[tmp]) {
      tmp++;
    }
    tmp-=i;
    tmp--;
    same[hPos]=tmp;
    tmp=((tmp - 3) & 0xFF) ^ val;
    hashVal2[hPos]=tmp;
    int h=head2[tmp];
    prev2[hPos]=h != -1 && hashVal2[h] == tmp ? h : hPos;
    head2[tmp]=hPos;
  }
  this.val=val;
}
