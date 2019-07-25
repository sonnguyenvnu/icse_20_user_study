/** 
 * @param writer The writer key with permission to write
 * @param key The key to set the value for
 * @param hash The hash of the key
 * @param depth The current depth in the champ (top = 0)
 * @param expected The expected value, if any, currently stored for this key
 * @param value The new value to map this key to
 * @param bitWidth The champ bitwidth
 * @param maxCollisions The maximum number of hash collision per layer in this champ
 * @param hasher The function to calculate the hash of keys
 * @param tid The transaction id for this write operation
 * @param storage The storage
 * @param ourHash The hash of the current champ node
 * @return A new champ and its hash after the put
 */
public CompletableFuture<Pair<Champ,Multihash>> put(PublicKeyHash owner,SigningPrivateKeyAndPublicHash writer,ByteArrayWrapper key,byte[] hash,int depth,MaybeMultihash expected,MaybeMultihash value,int bitWidth,int maxCollisions,Function<ByteArrayWrapper,byte[]> hasher,TransactionId tid,ContentAddressedStorage storage,Multihash ourHash){
  int bitpos=mask(hash,depth,bitWidth);
  if (dataMap.get(bitpos)) {
    int index=getIndex(this.dataMap,bitpos);
    HashPrefixPayload payload=contents[index];
    KeyElement[] mappings=payload.mappings;
    for (int payloadIndex=0; payloadIndex < mappings.length; payloadIndex++) {
      KeyElement mapping=mappings[payloadIndex];
      final ByteArrayWrapper currentKey=mapping.key;
      final MaybeMultihash currentVal=mapping.valueHash;
      if (currentKey.equals(key)) {
        if (!currentVal.equals(expected)) {
          CompletableFuture<Pair<Champ,Multihash>> err=new CompletableFuture<>();
          err.completeExceptionally(new MutableTree.CasException(currentVal,expected));
          return err;
        }
        Champ champ=copyAndSetValue(index,payloadIndex,value);
        return storage.put(owner,writer,champ.serialize(),tid).thenApply(h -> new Pair<>(champ,h));
      }
    }
    if (mappings.length < maxCollisions) {
      Champ champ=insertIntoPrefix(index,key,value);
      return storage.put(owner,writer,champ.serialize(),tid).thenApply(h -> new Pair<>(champ,h));
    }
    return pushMappingsDownALevel(owner,writer,mappings,key,hash,value,depth + 1,bitWidth,maxCollisions,hasher,tid,storage).thenCompose(p -> {
      Champ champ=copyAndMigrateFromInlineToNode(bitpos,p);
      return storage.put(owner,writer,champ.serialize(),tid).thenApply(h -> new Pair<>(champ,h));
    }
);
  }
 else   if (nodeMap.get(bitpos)) {
    return getChild(hash,depth,bitWidth,storage).thenCompose(child -> child.right.get().put(owner,writer,key,hash,depth + 1,expected,value,bitWidth,maxCollisions,hasher,tid,storage,child.left).thenCompose(newChild -> {
      if (newChild.right.equals(child.left))       return CompletableFuture.completedFuture(new Pair<>(this,ourHash));
      Champ champ=overwriteChildLink(bitpos,newChild);
      return storage.put(owner,writer,champ.serialize(),tid).thenApply(h -> new Pair<>(champ,h));
    }
));
  }
 else {
    Champ champ=addNewPrefix(bitpos,key,value);
    return storage.put(owner,writer,champ.serialize(),tid).thenApply(h -> new Pair<>(champ,h));
  }
}
