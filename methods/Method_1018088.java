@Override public FeedManagerTemplate disable(FeedManagerTemplate.ID id){
  JcrFeedTemplate template=(JcrFeedTemplate)findById(id);
  if (template != null) {
    if (template.isEnabled()) {
      template.disable();
      addPostFeedChangeAction(template,ChangeType.UPDATE);
      return update(template);
    }
    return template;
  }
 else {
    throw new MetadataRepositoryException("Unable to find template with id" + id);
  }
}
