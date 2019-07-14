/** 
 * Parse a chunk of code and extract the size() command and its contents. Also goes after fullScreen(), smooth(), and noSmooth().
 * @param code The code from the main tab in the sketch
 * @param fussy true if it should show an error message if bad size()
 * @return null if there was an error, otherwise an array (might contain some/all nulls)
 */
static public SurfaceInfo parseSketchSize(String code,boolean fussy) throws SketchException {
  String uncommented=scrubComments(code);
  Mode mode=parseMode(uncommented);
  String searchArea=null;
  if (mode == Mode.JAVA) {
    searchArea=null;
  }
 else   if (mode == Mode.ACTIVE) {
    MatchResult setupMatch=findInCurrentScope(VOID_SETUP_REGEX,uncommented);
    if (setupMatch != null) {
      int start=uncommented.indexOf("{",setupMatch.end());
      if (start >= 0) {
        MatchResult match=findInCurrentScope(CLOSING_BRACE,uncommented,start);
        if (match != null) {
          searchArea=uncommented.substring(start + 1,match.end() - 1);
        }
 else {
          throw new SketchException("Found a { that's missing a matching }",false);
        }
      }
    }
  }
 else   if (mode == Mode.STATIC) {
    searchArea=uncommented;
  }
  if (searchArea == null) {
    return new SurfaceInfo();
  }
  StringList extraStatements=new StringList();
  String[] smoothContents=matchMethod("smooth",searchArea);
  if (smoothContents != null) {
    extraStatements.append(smoothContents[0]);
  }
  String[] noContents=matchMethod("noSmooth",searchArea);
  if (noContents != null) {
    if (extraStatements.size() != 0) {
      throw new SketchException("smooth() and noSmooth() cannot be used in the same sketch");
    }
 else {
      extraStatements.append(noContents[0]);
    }
  }
  String[] pixelDensityContents=matchMethod("pixelDensity",searchArea);
  if (pixelDensityContents != null) {
    extraStatements.append(pixelDensityContents[0]);
  }
 else {
    pixelDensityContents=matchDensityMess(searchArea);
    if (pixelDensityContents != null) {
      extraStatements.append(pixelDensityContents[0]);
    }
  }
  String[] sizeContents=matchMethod("size",searchArea);
  String[] fullContents=matchMethod("fullScreen",searchArea);
  if (sizeContents != null && fullContents != null) {
    throw new SketchException("size() and fullScreen() cannot be used in the same sketch",false);
  }
  if (sizeContents != null) {
    StringList args=breakCommas(sizeContents[1]);
    SurfaceInfo info=new SurfaceInfo();
    info.addStatement(sizeContents[0]);
    info.width=args.get(0).trim();
    info.height=args.get(1).trim();
    info.renderer=(args.size() >= 3) ? args.get(2).trim() : null;
    info.path=(args.size() >= 4) ? args.get(3).trim() : null;
    if (info.hasOldSyntax()) {
      throw new SketchException("Please update your code to continue.",false);
    }
    if (info.hasBadSize() && fussy) {
      final String message="The size of this sketch could not be determined from your code.\n" + "Use only numbers (not variables) for the size() command.\n" + "Read the size() reference for more details.";
      EventQueue.invokeLater(() -> {
        Messages.showWarning("Could not find sketch size",message,null);
      }
);
      throw new SketchException("Please fix the size() line to continue.",false);
    }
    info.addStatements(extraStatements);
    info.checkEmpty();
    return info;
  }
  if (fullContents != null) {
    SurfaceInfo info=new SurfaceInfo();
    info.addStatement(fullContents[0]);
    StringList args=breakCommas(fullContents[1]);
    if (args.size() > 0) {
      String args0=args.get(0).trim();
      if (args.size() == 1) {
        if (args0.equals("SPAN") || PApplet.parseInt(args0,-1) != -1) {
          info.display=args0;
        }
 else {
          info.renderer=args0;
        }
      }
 else       if (args.size() == 2) {
        info.renderer=args0;
        info.display=args.get(1).trim();
      }
 else {
        throw new SketchException("That's too many parameters for fullScreen()");
      }
    }
    info.width="displayWidth";
    info.height="displayHeight";
    info.addStatements(extraStatements);
    info.checkEmpty();
    return info;
  }
  if (extraStatements.size() != 0) {
    SurfaceInfo info=new SurfaceInfo();
    info.addStatements(extraStatements);
    return info;
  }
  return new SurfaceInfo();
}
