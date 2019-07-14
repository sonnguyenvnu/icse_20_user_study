public boolean getIsPraised(final long userId){
  if (userId <= 0) {
    isPraised=false;
  }
 else   if (isPraised == null) {
    isPraised=isContain(getPraiseUserIdList(),userId);
  }
  return value(isPraised);
}
