/** 
 * Creates track selections for an array of track selection definitions, with at most one multi-track adaptive selection.
 * @param definitions The list of track selection {@link Definition definitions}. May include null values.
 * @param adaptiveTrackSelectionFactory A factory for the multi-track adaptive track selection.
 * @return The array of created track selection. For null entries in {@code definitions} returnsnull values.
 */
public static @NullableType TrackSelection[] createTrackSelectionsForDefinitions(@NullableType Definition[] definitions,AdaptiveTrackSelectionFactory adaptiveTrackSelectionFactory){
  TrackSelection[] selections=new TrackSelection[definitions.length];
  boolean createdAdaptiveTrackSelection=false;
  for (int i=0; i < definitions.length; i++) {
    Definition definition=definitions[i];
    if (definition == null) {
      continue;
    }
    if (definition.tracks.length > 1 && !createdAdaptiveTrackSelection) {
      createdAdaptiveTrackSelection=true;
      selections[i]=adaptiveTrackSelectionFactory.createAdaptiveTrackSelection(definition);
    }
 else {
      selections[i]=new FixedTrackSelection(definition.group,definition.tracks[0]);
    }
  }
  return selections;
}
