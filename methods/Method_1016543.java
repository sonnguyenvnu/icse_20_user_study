/** 
 * Formats the given string. 
 */
public String format(String raw){
  Matcher matcher=delimiterPattern.matcher(raw);
  if (!matcher.find()) {
    throw new IllegalArgumentException("Unable to find delimiter regex " + delimiterPattern);
  }
 else {
    if (hasYearToken) {
      if (matchesLicenseWithYearToken(raw,matcher)) {
        return raw;
      }
 else {
        return licenseHeaderWithYearTokenReplaced + raw.substring(matcher.start());
      }
    }
 else     if (matcher.start() == licenseHeader.length() && raw.startsWith(licenseHeader)) {
      return raw;
    }
 else {
      return licenseHeader + raw.substring(matcher.start());
    }
  }
}
