private <R>MetadataCommand<R> wrap(final MetadataCommand<R> cmd,final boolean readOnly){
  if (JcrMetadataAccess.hasActiveSession()) {
    return cmd;
  }
 else {
    return () -> {
      if (readOnly) {
        return jpaMetadataAccess.read(() -> cmd.execute());
      }
 else {
        return jpaMetadataAccess.commit(() -> cmd.execute());
      }
    }
;
  }
}
