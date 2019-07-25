private static ActiveShardCount get(final int value){
switch (value) {
case ACTIVE_SHARD_COUNT_DEFAULT:
    return DEFAULT;
case ALL_ACTIVE_SHARDS:
  return ALL;
case 1:
return ONE;
case 0:
return NONE;
default :
assert value > 1;
return new ActiveShardCount(value);
}
}
