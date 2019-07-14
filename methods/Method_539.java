public final float[][] scanFieldFloatArray2(char[] fieldName){
  matchStat=UNKNOWN;
  if (!charArrayCompare(fieldName)) {
    matchStat=NOT_MATCH_NAME;
    return null;
  }
  int offset=fieldName.length;
  char chLocal=charAt(bp + (offset++));
  if (chLocal != '[') {
    matchStat=NOT_MATCH_NAME;
    return null;
  }
  chLocal=charAt(bp + (offset++));
  float[][] arrayarray=new float[16][];
  int arrayarrayIndex=0;
  for (; ; ) {
    if (chLocal == '[') {
      chLocal=charAt(bp + (offset++));
      float[] array=new float[16];
      int arrayIndex=0;
      for (; ; ) {
        int start=bp + offset - 1;
        boolean negative=chLocal == '-';
        if (negative) {
          chLocal=charAt(bp + (offset++));
        }
        if (chLocal >= '0' && chLocal <= '9') {
          int intVal=chLocal - '0';
          for (; ; ) {
            chLocal=charAt(bp + (offset++));
            if (chLocal >= '0' && chLocal <= '9') {
              intVal=intVal * 10 + (chLocal - '0');
              continue;
            }
 else {
              break;
            }
          }
          int power=1;
          if (chLocal == '.') {
            chLocal=charAt(bp + (offset++));
            if (chLocal >= '0' && chLocal <= '9') {
              intVal=intVal * 10 + (chLocal - '0');
              power=10;
              for (; ; ) {
                chLocal=charAt(bp + (offset++));
                if (chLocal >= '0' && chLocal <= '9') {
                  intVal=intVal * 10 + (chLocal - '0');
                  power*=10;
                  continue;
                }
 else {
                  break;
                }
              }
            }
 else {
              matchStat=NOT_MATCH;
              return null;
            }
          }
          boolean exp=chLocal == 'e' || chLocal == 'E';
          if (exp) {
            chLocal=charAt(bp + (offset++));
            if (chLocal == '+' || chLocal == '-') {
              chLocal=charAt(bp + (offset++));
            }
            for (; ; ) {
              if (chLocal >= '0' && chLocal <= '9') {
                chLocal=charAt(bp + (offset++));
              }
 else {
                break;
              }
            }
          }
          int count=bp + offset - start - 1;
          float value;
          if (!exp && count < 10) {
            value=((float)intVal) / power;
            if (negative) {
              value=-value;
            }
          }
 else {
            String text=this.subString(start,count);
            value=Float.parseFloat(text);
          }
          if (arrayIndex >= array.length) {
            float[] tmp=new float[array.length * 3 / 2];
            System.arraycopy(array,0,tmp,0,arrayIndex);
            array=tmp;
          }
          array[arrayIndex++]=value;
          if (chLocal == ',') {
            chLocal=charAt(bp + (offset++));
          }
 else           if (chLocal == ']') {
            chLocal=charAt(bp + (offset++));
            break;
          }
        }
 else {
          matchStat=NOT_MATCH;
          return null;
        }
      }
      if (arrayIndex != array.length) {
        float[] tmp=new float[arrayIndex];
        System.arraycopy(array,0,tmp,0,arrayIndex);
        array=tmp;
      }
      if (arrayarrayIndex >= arrayarray.length) {
        float[][] tmp=new float[arrayarray.length * 3 / 2][];
        System.arraycopy(array,0,tmp,0,arrayIndex);
        arrayarray=tmp;
      }
      arrayarray[arrayarrayIndex++]=array;
      if (chLocal == ',') {
        chLocal=charAt(bp + (offset++));
      }
 else       if (chLocal == ']') {
        chLocal=charAt(bp + (offset++));
        break;
      }
    }
 else {
      break;
    }
  }
  if (arrayarrayIndex != arrayarray.length) {
    float[][] tmp=new float[arrayarrayIndex][];
    System.arraycopy(arrayarray,0,tmp,0,arrayarrayIndex);
    arrayarray=tmp;
  }
  if (chLocal == ',') {
    bp+=(offset - 1);
    this.next();
    matchStat=VALUE;
    token=JSONToken.COMMA;
    return arrayarray;
  }
  if (chLocal == '}') {
    chLocal=charAt(bp + (offset++));
    if (chLocal == ',') {
      token=JSONToken.COMMA;
      bp+=(offset - 1);
      this.next();
    }
 else     if (chLocal == ']') {
      token=JSONToken.RBRACKET;
      bp+=(offset - 1);
      this.next();
    }
 else     if (chLocal == '}') {
      token=JSONToken.RBRACE;
      bp+=(offset - 1);
      this.next();
    }
 else     if (chLocal == EOI) {
      bp+=(offset - 1);
      token=JSONToken.EOF;
      ch=EOI;
    }
 else {
      matchStat=NOT_MATCH;
      return null;
    }
    matchStat=END;
  }
 else {
    matchStat=NOT_MATCH;
    return null;
  }
  return arrayarray;
}
