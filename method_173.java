private void _XXXXX_(List<AlertPublishPlugin> toBeClosed){
  for (  AlertPublishPlugin p : toBeClosed) {
    try {
      p.close();
    }
 catch (    Exception e) {
      LOG.error("Error when close publish plugin {}!",p.getClass().getCanonicalName(),e);
    }
  }
}