void compute(){
  final int swA=(a_ == null) ? 0 : (a_.isEmpty()) ? 1 : (a_ instanceof UpdateSketch) ? 4 : (a_.isOrdered()) ? 3 : 2;
  final int swB=(b_ == null) ? 0 : (b_.isEmpty()) ? 1 : (b_ instanceof UpdateSketch) ? 4 : (b_.isOrdered()) ? 3 : 2;
  final int sw=(swA * 8) | swB;
switch (sw) {
case 0:
    thetaLong_=Long.MAX_VALUE;
  empty_=true;
break;
case 10:
case 11:
case 12:
Util.checkSeedHashes(seedHash_,a_.getSeedHash());
case 1:
case 2:
case 3:
case 4:
Util.checkSeedHashes(seedHash_,b_.getSeedHash());
thetaLong_=Long.MAX_VALUE;
empty_=true;
break;
case 9:
Util.checkSeedHashes(seedHash_,b_.getSeedHash());
case 8:
Util.checkSeedHashes(seedHash_,a_.getSeedHash());
thetaLong_=Long.MAX_VALUE;
empty_=true;
break;
case 17:
case 25:
case 33:
Util.checkSeedHashes(seedHash_,b_.getSeedHash());
case 16:
case 24:
case 32:
Util.checkSeedHashes(seedHash_,a_.getSeedHash());
thetaLong_=a_.getThetaLong();
empty_=false;
curCount_=a_.getRetainedEntries(true);
cache_=compactCache(a_.getCache(),curCount_,thetaLong_,false);
break;
case 18:
case 19:
case 34:
case 35:
Util.checkSeedHashes(seedHash_,a_.getSeedHash());
Util.checkSeedHashes(seedHash_,b_.getSeedHash());
thetaLong_=min(a_.getThetaLong(),b_.getThetaLong());
empty_=false;
convertBtoHT();
scanAllAsearchB();
break;
case 26:
case 27:
Util.checkSeedHashes(seedHash_,a_.getSeedHash());
Util.checkSeedHashes(seedHash_,b_.getSeedHash());
thetaLong_=min(a_.getThetaLong(),b_.getThetaLong());
empty_=false;
convertBtoHT();
scanEarlyStopAsearchB();
break;
case 20:
case 36:
Util.checkSeedHashes(seedHash_,a_.getSeedHash());
Util.checkSeedHashes(seedHash_,b_.getSeedHash());
thetaLong_=min(a_.getThetaLong(),b_.getThetaLong());
empty_=false;
lgArrLongsHT_=((UpdateSketch)b_).getLgArrLongs();
bHashTable_=b_.getCache();
scanAllAsearchB();
break;
case 28:
Util.checkSeedHashes(seedHash_,a_.getSeedHash());
Util.checkSeedHashes(seedHash_,b_.getSeedHash());
thetaLong_=min(a_.getThetaLong(),b_.getThetaLong());
empty_=false;
lgArrLongsHT_=((UpdateSketch)b_).getLgArrLongs();
bHashTable_=b_.getCache();
scanEarlyStopAsearchB();
break;
}
}
