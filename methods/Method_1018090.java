private MetadataAction wrap(final MetadataAction action,final boolean readOnly){
  if (JcrMetadataAccess.hasActiveSession()) {
    return action;
  }
 else {
    return () -> {
      if (readOnly) {
        jpaMetadataAccess.read(() -> {
          action.execute();
          return null;
        }
);
      }
 else {
        jpaMetadataAccess.commit(() -> {
          action.execute();
          return null;
        }
);
      }
    }
;
  }
}
