/** 
 * Loads a  {@link TransitionManager} object from a resource
 * @param resource The resource id of the transition manager to load
 * @return The loaded TransitionManager object
 * @throws android.content.res.Resources.NotFoundException when thetransition manager cannot be loaded
 */
@Nullable public TransitionManager inflateTransitionManager(int resource,@NonNull ViewGroup sceneRoot){
  XmlResourceParser parser=mContext.getResources().getXml(resource);
  try {
    return createTransitionManagerFromXml(parser,Xml.asAttributeSet(parser),sceneRoot);
  }
 catch (  XmlPullParserException e) {
    InflateException ex=new InflateException(e.getMessage());
    ex.initCause(e);
    throw ex;
  }
catch (  IOException e) {
    InflateException ex=new InflateException(parser.getPositionDescription() + ": " + e.getMessage());
    ex.initCause(e);
    throw ex;
  }
 finally {
    parser.close();
  }
}
