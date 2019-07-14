/** 
 * Exceptions where 'E' is pronounced where it usually wouldn't be, and also some cases where 'LE' transposition rules don't apply and the vowel needs to be encoded here
 * @return true if 'E' pronounced 
 */
boolean E_Pronounced_Exceptions(){
  if ((((m_current + 1) == m_last) && (StringAt((m_current - 3),5,"OCLES","ACLES","AKLES","") || StringAt(0,4,"INES","") || StringAt(0,5,"LOPES","ESTES","GOMES","NUNES","ALVES","ICKES","INNES","PERES","WAGES","NEVES","BENES","DONES","") || StringAt(0,6,"CORTES","CHAVES","VALDES","ROBLES","TORRES","FLORES","BORGES","NIEVES","MONTES","SOARES","VALLES","GEDDES","ANDRES","VIAJES","CALLES","FONTES","HERMES","ACEVES","BATRES","MATHES","") || StringAt(0,7,"DELORES","MORALES","DOLORES","ANGELES","ROSALES","MIRELES","LINARES","PERALES","PAREDES","BRIONES","SANCHES","CAZARES","REVELES","ESTEVES","ALVARES","MATTHES","SOLARES","CASARES","CACERES","STURGES","RAMIRES","FUNCHES","BENITES","FUENTES","PUENTES","TABARES","HENTGES","VALORES","") || StringAt(0,8,"GONZALES","MERCEDES","FAGUNDES","JOHANNES","GONSALES","BERMUDES","CESPEDES","BETANCES","TERRONES","DIOGENES","CORRALES","CABRALES","MARTINES","GRAJALES","") || StringAt(0,9,"CERVANTES","FERNANDES","GONCALVES","BENEVIDES","CIFUENTES","SIFUENTES","SERVANTES","HERNANDES","BENAVIDES","") || StringAt(0,10,"ARCHIMEDES","CARRIZALES","MAGALLANES",""))) || StringAt(m_current - 2,4,"FRED","DGES","DRED","GNES","") || StringAt((m_current - 5),7,"PROBLEM","RESPLEN","") || StringAt((m_current - 4),6,"REPLEN","") || StringAt((m_current - 3),4,"SPLE","")) {
    return true;
  }
  return false;
}
