private String[] _calcMatcherParts(final Matcher m){
  String currentLocalpart=null;
  String currentDomainpart=null;
  final String localPartDa;
  String localPartQs=null;
  final String domainPartDa;
  String domainPartDl=null;
  String personalString=null;
  if (ALLOW_QUOTED_IDENTIFIERS) {
    if (ALLOW_DOMAIN_LITERALS) {
      if (m.group(1) != null) {
        localPartDa=m.group(5);
        if (localPartDa == null) {
          localPartQs=m.group(6);
        }
        domainPartDa=m.group(7);
        if (domainPartDa == null) {
          domainPartDl=m.group(8);
        }
        currentLocalpart=(localPartDa == null ? localPartQs : localPartDa);
        currentDomainpart=(domainPartDa == null ? domainPartDl : domainPartDa);
        personalString=m.group(2);
        if (personalString == null && EXTRACT_CFWS_PERSONAL_NAMES) {
          personalString=m.group(9);
          personalString=removeAnyBounding('(',')',getFirstComment(personalString));
        }
      }
 else       if (m.group(10) != null) {
        localPartDa=m.group(12);
        if (localPartDa == null) {
          localPartQs=m.group(13);
        }
        domainPartDa=m.group(14);
        if (domainPartDa == null) {
          domainPartDl=m.group(15);
        }
        currentLocalpart=(localPartDa == null ? localPartQs : localPartDa);
        currentDomainpart=(domainPartDa == null ? domainPartDl : domainPartDa);
        if (EXTRACT_CFWS_PERSONAL_NAMES) {
          personalString=m.group(16);
          personalString=removeAnyBounding('(',')',getFirstComment(personalString));
        }
      }
    }
 else {
      if (m.group(1) != null) {
        localPartDa=m.group(5);
        if (localPartDa == null) {
          localPartQs=m.group(6);
        }
        currentLocalpart=(localPartDa == null ? localPartQs : localPartDa);
        currentDomainpart=m.group(7);
        personalString=m.group(2);
        if (personalString == null && EXTRACT_CFWS_PERSONAL_NAMES) {
          personalString=m.group(8);
          personalString=removeAnyBounding('(',')',getFirstComment(personalString));
        }
      }
 else       if (m.group(9) != null) {
        localPartDa=m.group(11);
        if (localPartDa == null) {
          localPartQs=m.group(12);
        }
        currentLocalpart=(localPartDa == null ? localPartQs : localPartDa);
        currentDomainpart=m.group(13);
        if (EXTRACT_CFWS_PERSONAL_NAMES) {
          personalString=m.group(14);
          personalString=removeAnyBounding('(',')',getFirstComment(personalString));
        }
      }
    }
  }
 else {
    localPartDa=m.group(3);
    if (localPartDa == null) {
      localPartQs=m.group(4);
    }
    domainPartDa=m.group(5);
    if (domainPartDa == null && ALLOW_DOMAIN_LITERALS) {
      domainPartDl=m.group(6);
    }
    currentLocalpart=(localPartDa == null ? localPartQs : localPartDa);
    currentDomainpart=(domainPartDa == null ? domainPartDl : domainPartDa);
    if (EXTRACT_CFWS_PERSONAL_NAMES) {
      personalString=m.group((ALLOW_DOMAIN_LITERALS ? 1 : 0) + 6);
      personalString=removeAnyBounding('(',')',getFirstComment(personalString));
    }
  }
  if (currentLocalpart != null) {
    currentLocalpart=currentLocalpart.trim();
  }
  if (currentDomainpart != null) {
    currentDomainpart=currentDomainpart.trim();
  }
  if (personalString != null) {
    personalString=personalString.trim();
    personalString=cleanupPersonalString(personalString);
  }
  final String testAddr=removeAnyBounding('"','"',currentLocalpart) + "@" + currentDomainpart;
  if (_ADDR_SPEC_PATTERN.matcher(testAddr).matches()) {
    currentLocalpart=removeAnyBounding('"','"',currentLocalpart);
  }
  return (new String[]{personalString,currentLocalpart,currentDomainpart});
}
