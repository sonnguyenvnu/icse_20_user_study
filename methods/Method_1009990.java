public int add(long v,long[] out){
  pending[inputCount++]=v;
  while (inputCompressable <= inputCount) {
    final long nextData=pending[(inputCompressable - 1)];
    while ((nextData >= maxFitPlus1) && (minBits < NUM_DATA_BITS)) {
      if ((minBits == 7) && (inputCompressable == 8) && (nextData < (maxFitPlus1 << 4))) {
        break;
      }
 else       if ((minBits == 8) && (inputCompressable == 7) && (nextData < (maxFitPlus1 << 4))) {
        break;
      }
 else {
        minBits++;
        maxFitPlus1<<=1;
        if ((inputCompressable * minBits) > NUM_DATA_BITS) {
          inputCompressable--;
          break;
        }
      }
    }
    inputCompressable++;
    if ((inputCompressable * minBits) > NUM_DATA_BITS) {
      inputCompressable--;
      assert inputCompressable > 0;
      while ((inputCompressable * (minBits + 1)) <= NUM_DATA_BITS) {
        minBits++;
      }
      long s9;
      final int consumed;
switch (minBits) {
case 60:
        s9=STATUS_1NUM_60BITS;
      s9|=pending[0] << 4;
    consumed=1;
  break;
case 30:
s9=STATUS_2NUM_30BITS;
s9|=pending[0] << 4;
s9|=pending[1] << 34;
consumed=2;
break;
case 20:
s9=STATUS_3NUM_20BITS;
s9|=pending[0] << 4;
s9|=pending[1] << 24;
s9|=pending[2] << 44;
consumed=3;
break;
case 15:
s9=STATUS_4NUM_15BITS;
s9|=pending[0] << 4;
s9|=pending[1] << 19;
s9|=pending[2] << 34;
s9|=pending[3] << 49;
consumed=4;
break;
case 12:
s9=STATUS_5NUM_12BITS;
s9|=pending[0] << 4;
s9|=pending[1] << 16;
s9|=pending[2] << 28;
s9|=pending[3] << 40;
s9|=pending[4] << 52;
consumed=5;
break;
case 10:
s9=STATUS_6NUM_10BITS;
s9|=pending[0] << 4;
s9|=pending[1] << 14;
s9|=pending[2] << 24;
s9|=pending[3] << 34;
s9|=pending[4] << 44;
s9|=pending[5] << 54;
consumed=6;
break;
case 8:
s9=STATUS_7NUM_8BITS;
s9|=pending[0] << 4;
s9|=pending[1] << 12;
s9|=pending[2] << 20;
s9|=pending[3] << 28;
s9|=pending[4] << 36;
s9|=pending[5] << 44;
s9|=pending[6] << 52;
consumed=7;
break;
case 7:
s9=STATUS_8NUM_7BITS;
s9|=pending[0] << 4;
s9|=pending[1] << 11;
s9|=pending[2] << 18;
s9|=pending[3] << 25;
s9|=pending[4] << 32;
s9|=pending[5] << 39;
s9|=pending[6] << 46;
s9|=pending[7] << 53;
consumed=8;
break;
case 6:
s9=STATUS_10NUM_6BITS;
s9|=pending[0] << 4;
s9|=pending[1] << 10;
s9|=pending[2] << 16;
s9|=pending[3] << 22;
s9|=pending[4] << 28;
s9|=pending[5] << 34;
s9|=pending[6] << 40;
s9|=pending[7] << 46;
s9|=pending[8] << 52;
s9|=pending[9] << 58;
consumed=10;
break;
case 5:
s9=STATUS_12NUM_5BITS;
s9|=pending[0] << 4;
s9|=pending[1] << 9;
s9|=pending[2] << 14;
s9|=pending[3] << 19;
s9|=pending[4] << 24;
s9|=pending[5] << 29;
s9|=pending[6] << 34;
s9|=pending[7] << 39;
s9|=pending[8] << 44;
s9|=pending[9] << 49;
s9|=pending[10] << 54;
s9|=pending[11] << 59;
consumed=12;
break;
case 4:
s9=STATUS_15NUM_4BITS;
s9|=pending[0] << 4;
s9|=pending[1] << 8;
s9|=pending[2] << 12;
s9|=pending[3] << 16;
s9|=pending[4] << 20;
s9|=pending[5] << 24;
s9|=pending[6] << 28;
s9|=pending[7] << 32;
s9|=pending[8] << 36;
s9|=pending[9] << 40;
s9|=pending[10] << 44;
s9|=pending[11] << 48;
s9|=pending[12] << 52;
s9|=pending[13] << 56;
s9|=pending[14] << 60;
consumed=15;
break;
case 3:
s9=STATUS_20NUM_3BITS;
s9|=pending[0] << 4;
s9|=pending[1] << 7;
s9|=pending[2] << 10;
s9|=pending[3] << 13;
s9|=pending[4] << 16;
s9|=pending[5] << 19;
s9|=pending[6] << 22;
s9|=pending[7] << 25;
s9|=pending[8] << 28;
s9|=pending[9] << 31;
s9|=pending[10] << 34;
s9|=pending[11] << 37;
s9|=pending[12] << 40;
s9|=pending[13] << 43;
s9|=pending[14] << 46;
s9|=pending[15] << 49;
s9|=pending[16] << 52;
s9|=pending[17] << 55;
s9|=pending[18] << 58;
s9|=pending[19] << 61;
consumed=20;
break;
case 2:
s9=STATUS_30NUM_2BITS;
s9|=pending[0] << 4;
s9|=pending[1] << 6;
s9|=pending[2] << 8;
s9|=pending[3] << 10;
s9|=pending[4] << 12;
s9|=pending[5] << 14;
s9|=pending[6] << 16;
s9|=pending[7] << 18;
s9|=pending[8] << 20;
s9|=pending[9] << 22;
s9|=pending[10] << 24;
s9|=pending[11] << 26;
s9|=pending[12] << 28;
s9|=pending[13] << 30;
s9|=pending[14] << 32;
s9|=pending[15] << 34;
s9|=pending[16] << 36;
s9|=pending[17] << 38;
s9|=pending[18] << 40;
s9|=pending[19] << 42;
s9|=pending[20] << 44;
s9|=pending[21] << 46;
s9|=pending[22] << 48;
s9|=pending[23] << 50;
s9|=pending[24] << 52;
s9|=pending[25] << 54;
s9|=pending[26] << 56;
s9|=pending[27] << 58;
s9|=pending[28] << 60;
s9|=pending[29] << 62;
consumed=30;
break;
case 1:
s9=STATUS_60NUM_1BITS;
s9|=pending[0] << 4;
s9|=pending[1] << 5;
s9|=pending[2] << 6;
s9|=pending[3] << 7;
s9|=pending[4] << 8;
s9|=pending[5] << 9;
s9|=pending[6] << 10;
s9|=pending[7] << 11;
s9|=pending[8] << 12;
s9|=pending[9] << 13;
s9|=pending[10] << 14;
s9|=pending[11] << 15;
s9|=pending[12] << 16;
s9|=pending[13] << 17;
s9|=pending[14] << 18;
s9|=pending[15] << 19;
s9|=pending[16] << 20;
s9|=pending[17] << 21;
s9|=pending[18] << 22;
s9|=pending[19] << 23;
s9|=pending[20] << 24;
s9|=pending[21] << 25;
s9|=pending[22] << 26;
s9|=pending[23] << 27;
s9|=pending[24] << 28;
s9|=pending[25] << 29;
s9|=pending[26] << 30;
s9|=pending[27] << 31;
s9|=pending[28] << 32;
s9|=pending[29] << 33;
s9|=pending[30] << 34;
s9|=pending[31] << 35;
s9|=pending[32] << 36;
s9|=pending[33] << 37;
s9|=pending[34] << 38;
s9|=pending[35] << 39;
s9|=pending[36] << 40;
s9|=pending[37] << 41;
s9|=pending[38] << 42;
s9|=pending[39] << 43;
s9|=pending[40] << 44;
s9|=pending[41] << 45;
s9|=pending[42] << 46;
s9|=pending[43] << 47;
s9|=pending[44] << 48;
s9|=pending[45] << 49;
s9|=pending[46] << 50;
s9|=pending[47] << 51;
s9|=pending[48] << 52;
s9|=pending[49] << 53;
s9|=pending[50] << 54;
s9|=pending[51] << 55;
s9|=pending[52] << 56;
s9|=pending[53] << 57;
s9|=pending[54] << 58;
s9|=pending[55] << 59;
s9|=pending[56] << 60;
s9|=pending[57] << 61;
s9|=pending[58] << 62;
s9|=pending[59] << 63;
consumed=60;
break;
default :
assert false;
s9=0;
consumed=60;
}
final int leftover=inputCount - consumed;
assert leftover >= 0 : "consumed=" + consumed + " vs " + inputCompressable;
reset();
System.arraycopy(pending,consumed,pending,0,leftover);
inputCount=leftover;
out[0]=s9;
return consumed;
}
}
return 0;
}
