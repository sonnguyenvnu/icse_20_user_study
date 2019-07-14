private void createVersionAttr(StringBuilder buffer){
  buffer.append("<pmd xmlns=\"http://pmd.sourceforge.net/report/2.0.0\"").append(PMD.EOL).append("    xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"").append(PMD.EOL).append("    xsi:schemaLocation=\"http://pmd.sourceforge.net/report/2.0.0 http://pmd.sourceforge.net/report_2_0_0.xsd\"").append(PMD.EOL).append("    version=\"").append(PMDVersion.VERSION).append('"');
}
