/** 
 * Links to a webpage either in the same window or in a new window. The complete URL must be specified. <h3>Advanced</h3> Link to an external page without all the muss. <p> When run with an applet, uses the browser to open the url, for applications, attempts to launch a browser with the url.
 * @param url the complete URL, as a String in quotes
 */
public void link(String url){
  try {
    if (Desktop.isDesktopSupported()) {
      Desktop.getDesktop().browse(new URI(url));
    }
 else {
      launch(url);
    }
  }
 catch (  IOException e) {
    printStackTrace(e);
  }
catch (  URISyntaxException e) {
    printStackTrace(e);
  }
}
