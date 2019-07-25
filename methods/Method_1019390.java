/** 
 * Called when deserialising JSON, to re-create the object graph based upon element/relationship IDs.
 */
public void hydrate(){
  if (viewSet == null) {
    viewSet=createViewSet();
  }
  if (documentation == null) {
    documentation=createDocumentation();
  }
  hydrateModel();
  hydrateViewSet();
  hydrateDocumentation();
}
