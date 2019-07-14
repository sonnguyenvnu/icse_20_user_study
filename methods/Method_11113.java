private void initShineParams(ShineParams shineParams,RxShineButton rxShineButton){
  shineCount=shineParams.shineCount;
  turnAngle=shineParams.shineTurnAngle;
  smallOffsetAngle=shineParams.smallShineOffsetAngle;
  enableFlashing=shineParams.enableFlashing;
  allowRandomColor=shineParams.allowRandomColor;
  shineDistanceMultiple=shineParams.shineDistanceMultiple;
  animDuration=shineParams.animDuration;
  clickAnimDuration=shineParams.clickAnimDuration;
  smallShineColor=shineParams.smallShineColor;
  bigShineColor=shineParams.bigShineColor;
  shineSize=shineParams.shineSize;
  if (smallShineColor == 0) {
    smallShineColor=colorRandom[6];
  }
  if (bigShineColor == 0) {
    bigShineColor=rxShineButton.getColor();
  }
}
