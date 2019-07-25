public static <T extends RecordTemplate>PatchRequest<T> diff(T original,T revised){
  PatchTree patch=PatchCreator.diff(original,revised);
  return PatchRequest.createFromPatchDocument(patch.getDataMap());
}
