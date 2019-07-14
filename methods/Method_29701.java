@Facing public int toggleFacing(){
switch (mFacing) {
case FACING_BACK:
    setFacing(FACING_FRONT);
  break;
case FACING_FRONT:
setFacing(FACING_BACK);
break;
}
return mFacing;
}
