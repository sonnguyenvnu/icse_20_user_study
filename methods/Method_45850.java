private static int getHashTableSize(int inputSize){
  assert (MAX_HASH_TABLE_SIZE >= 256);
  int hashTableSize=256;
  while (hashTableSize < MAX_HASH_TABLE_SIZE && hashTableSize < inputSize) {
    hashTableSize<<=1;
  }
  assert 0 == (hashTableSize & (hashTableSize - 1)) : "hash must be power of two";
  assert hashTableSize <= MAX_HASH_TABLE_SIZE : "hash table too large";
  return hashTableSize;
}
