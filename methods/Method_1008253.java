/** 
 * Creates a move decision for the shard being able to remain on its current node, so the shard won't be forced to move to another node.
 */
public static MoveDecision stay(Decision canRemainDecision){
  if (canRemainDecision != null) {
    assert canRemainDecision.type() != Type.NO;
    return new MoveDecision(canRemainDecision,null,AllocationDecision.NO_ATTEMPT,null,null,0);
  }
 else {
    return CACHED_STAY_DECISION;
  }
}
