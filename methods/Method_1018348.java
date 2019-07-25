public LinkCollectingAssociationHandler nested(){
  return nested ? this : new LinkCollectingAssociationHandler(entities,basePath,associations,true);
}
