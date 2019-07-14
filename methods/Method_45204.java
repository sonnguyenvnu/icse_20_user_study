public static MocoEventAction async(final MocoEventAction action,final LatencyProcedure procedure){
  return new MocoAsyncAction(checkNotNull(action,"Action should not be null"),checkNotNull(procedure,"Procedure should not be null"));
}
