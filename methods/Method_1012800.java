/** 
 * Parses the media resource.
 */
protected void parse(){
  if (isBlank(fileNameWithoutExtension)) {
    return;
  }
  String tmpName=removeGroupNameFromBeginning(fileNameWithoutExtension);
  tmpName=removeFilenameEndMetadata(tmpName);
  tmpName=tmpName.replaceAll("_"," ");
  if (isBlank(fileName)) {
    return;
  }
  Matcher matcher=ANIME_HASH.matcher(tmpName);
  if (matcher.find()) {
    tmpName=matcher.replaceAll("");
  }
  matcher=SERIES_EPISODE_EPISODE.matcher(tmpName);
  if (matcher.find()) {
    classification=VideoClassification.SERIES;
    season=Integer.parseInt(matcher.group(1));
    episode=Integer.parseInt(matcher.group(2));
    String[] splitName=SERIES_EPISODE_EPISODE.split(tmpName);
    if (splitName.length > 1) {
      String tmpEpisodeName=splitName[1];
      tmpEpisodeName=normalizeSpaces(tmpEpisodeName);
      if (tmpEpisodeName.length() > 0) {
        episodeName=convertFormattedNameToTitleCase(tmpEpisodeName);
      }
    }
    name=convertFormattedNameToTitleCase(normalizeSpaces(removeFilenameEndMetadata(splitName[0])));
    return;
  }
  matcher=SERIES_EPISODE.matcher(tmpName);
  if (matcher.find()) {
    classification=VideoClassification.SERIES;
    season=Integer.parseInt(matcher.group(1));
    episode=Integer.parseInt(matcher.group(2));
    String[] splitName=SERIES_EPISODE.split(tmpName);
    if (splitName.length > 1) {
      String tmpEpisodeName=splitName[1];
      tmpEpisodeName=normalizeSpaces(tmpEpisodeName);
      if (tmpEpisodeName.length() > 0) {
        episodeName=convertFormattedNameToTitleCase(tmpEpisodeName);
      }
    }
    name=convertFormattedNameToTitleCase(normalizeSpaces(removeFilenameEndMetadata(splitName[0])));
    return;
  }
  matcher=ANIME_SERIES_EPISODE.matcher(tmpName);
  matcher=SERIES_DATE.matcher(tmpName);
  if (matcher.find()) {
    classification=VideoClassification.SERIES;
    season=Integer.parseInt(matcher.group(1));
    year=season;
    episode=Integer.parseInt(matcher.group(2) + matcher.group(3));
    String[] splitName=SERIES_DATE.split(tmpName);
    if (splitName.length > 1) {
      String tmpEpisodeName=splitName[1];
      tmpEpisodeName=normalizeSpaces(tmpEpisodeName);
      if (tmpEpisodeName.length() > 0) {
        episodeName=convertFormattedNameToTitleCase(tmpEpisodeName);
      }
    }
    name=convertFormattedNameToTitleCase(normalizeSpaces(removeFilenameEndMetadata(splitName[0])));
    return;
  }
  matcher=MOVIE_YEAR.matcher(tmpName);
  if (matcher.find()) {
    classification=VideoClassification.MOVIE;
    tmpName=extractEdition(tmpName);
    String yearString=matcher.group(2);
    if (isBlank(yearString)) {
      yearString=matcher.group(1);
    }
    year=Integer.parseInt(yearString);
    name=convertFormattedNameToTitleCase(splitClean(MOVIE_YEAR,tmpName));
    return;
  }
  matcher=ANIME_SERIES_EPISODE_EPISODE.matcher(tmpName);
  if (matcher.find()) {
    classification=VideoClassification.SERIES;
    season=isBlank(matcher.group(1)) ? 1 : Integer.parseInt(matcher.group(1));
    episode=Integer.parseInt(matcher.group(2));
    String[] splitName=ANIME_SERIES_EPISODE_EPISODE.split(tmpName);
    if (splitName.length > 1) {
      String tmpEpisodeName=splitName[1];
      tmpEpisodeName=normalizeSpaces(tmpEpisodeName);
      if (tmpEpisodeName.length() > 0) {
        episodeName=convertFormattedNameToTitleCase(tmpEpisodeName);
      }
    }
    name=convertFormattedNameToTitleCase(normalizeSpaces(removeFilenameEndMetadata(splitName[0])));
    return;
  }
  if (matcher.find()) {
    classification=VideoClassification.SERIES;
    season=isBlank(matcher.group(1)) ? 1 : Integer.parseInt(matcher.group(1));
    episode=Integer.parseInt(matcher.group(2));
    String[] splitName=ANIME_SERIES_EPISODE.split(tmpName);
    if (splitName.length > 1) {
      String tmpEpisodeName=splitName[1];
      tmpEpisodeName=normalizeSpaces(tmpEpisodeName);
      if (tmpEpisodeName.length() > 0) {
        episodeName=convertFormattedNameToTitleCase(tmpEpisodeName);
      }
    }
    name=convertFormattedNameToTitleCase(normalizeSpaces(removeFilenameEndMetadata(splitName[0])));
    return;
  }
  classification=VideoClassification.MOVIE;
  tmpName=extractEdition(tmpName);
  name=convertFormattedNameToTitleCase(normalizeSpaces(removeFilenameEndMetadata(tmpName)));
}
