/** 
 * ??+1 ???? true? ??????buff??????????false ???????
 */
boolean moveCursor(){
  if (this.cursor < this.available - 1) {
    this.cursor++;
    this.segmentBuff[this.cursor]=CharacterUtil.regularize(this.segmentBuff[this.cursor],cfg.isEnableLowercase());
    this.charTypes[this.cursor]=CharacterUtil.identifyCharType(this.segmentBuff[this.cursor]);
    return true;
  }
 else {
    return false;
  }
}
