/** 
 * ???buff??????????
 */
void initCursor(){
  this.cursor=0;
  this.segmentBuff[this.cursor]=CharacterUtil.regularize(this.segmentBuff[this.cursor],cfg.isEnableLowercase());
  this.charTypes[this.cursor]=CharacterUtil.identifyCharType(this.segmentBuff[this.cursor]);
}
