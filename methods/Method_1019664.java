/** 
 * Parse the annotation content. It splits into 3 section: <ul> <li>replacement</li> <li>before</li> <li>after</li> </ul>
 */
private void parse(){
  int indexBefore=content.indexOf(BEFORE_LABEL);
  int indexAfter=content.indexOf(AFTER_LABEL);
  if (indexBefore < 0 && indexAfter < 0) {
    if (content.length() > 0) {
      replacement=content.toString();
    }
  }
 else   if (indexBefore < 0 || indexAfter < 0) {
    if (indexBefore > 0 || indexAfter > 0) {
      replacement=content.substring(0,Math.max(indexBefore,indexAfter));
    }
    if (indexBefore < 0) {
      after=content.substring(indexAfter + AFTER_LABEL.length(),content.length());
    }
 else {
      before=content.substring(indexBefore + BEFORE_LABEL.length(),content.length());
    }
  }
 else   if (indexBefore < indexAfter) {
    if (indexBefore > 0) {
      replacement=content.substring(0,indexBefore);
    }
    before=content.substring(indexBefore + BEFORE_LABEL.length(),indexAfter);
    after=content.substring(indexAfter + AFTER_LABEL.length(),content.length());
  }
 else {
    if (indexAfter > 0) {
      replacement=content.substring(0,indexAfter);
    }
    after=content.substring(indexAfter + AFTER_LABEL.length(),indexBefore);
    before=content.substring(indexBefore + BEFORE_LABEL.length(),content.length());
  }
}
