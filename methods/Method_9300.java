@Override public boolean onPreIme(){
  if (SecretMediaViewer.hasInstance() && SecretMediaViewer.getInstance().isVisible()) {
    SecretMediaViewer.getInstance().closePhoto(true,false);
    return true;
  }
 else   if (PhotoViewer.hasInstance() && PhotoViewer.getInstance().isVisible()) {
    PhotoViewer.getInstance().closePhoto(true,false);
    return true;
  }
 else   if (ArticleViewer.hasInstance() && ArticleViewer.getInstance().isVisible()) {
    ArticleViewer.getInstance().close(true,false);
    return true;
  }
  return false;
}
