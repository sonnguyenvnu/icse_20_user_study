public final String language(){
  String language="en";
  if (this.host == null)   return language;
  final int pos=this.host.lastIndexOf('.');
  String host_tld=this.host.substring(pos + 1).toLowerCase();
  if (pos == 0)   return language;
  int length=this.host.length() - pos - 1;
switch (length) {
case 2:
    char firstletter=host_tld.charAt(0);
switch (firstletter) {
case 'a':
    if (host_tld.equals("au")) {
      language="en";
    }
 else     if (host_tld.equals("at")) {
      language="de";
    }
 else     if (host_tld.equals("ar")) {
      language="es";
    }
 else     if (host_tld.equals("ae")) {
      language="ar";
    }
 else     if (host_tld.equals("am")) {
      language="hy";
    }
 else     if (host_tld.equals("ac")) {
      language="en";
    }
 else     if (host_tld.equals("az")) {
      language="az";
    }
 else     if (host_tld.equals("ag")) {
      language="en";
    }
 else     if (host_tld.equals("as")) {
      language="en";
    }
 else     if (host_tld.equals("al")) {
      language="sq";
    }
 else     if (host_tld.equals("ad")) {
      language="ca";
    }
 else     if (host_tld.equals("ao")) {
      language="pt";
    }
 else     if (host_tld.equals("ai")) {
      language="en";
    }
 else     if (host_tld.equals("af")) {
      language="ps";
    }
 else     if (host_tld.equals("an")) {
      language="nl";
    }
 else     if (host_tld.equals("aq")) {
      language="en";
    }
 else     if (host_tld.equals("aw")) {
      language="nl";
    }
 else     if (host_tld.equals("ax")) {
      language="sv";
    }
  break;
case 'b':
if (host_tld.equals("br")) {
  language="pt";
}
 else if (host_tld.equals("be")) {
  language="nl";
}
 else if (host_tld.equals("bg")) {
  language="bg";
}
 else if (host_tld.equals("bz")) {
  language="en";
}
 else if (host_tld.equals("ba")) {
  language="sh";
}
 else if (host_tld.equals("by")) {
  language="be";
}
 else if (host_tld.equals("bo")) {
  language="es";
}
 else if (host_tld.equals("bd")) {
  language="bn";
}
 else if (host_tld.equals("bw")) {
  language="tn";
}
 else if (host_tld.equals("bh")) {
  language="ar";
}
 else if (host_tld.equals("bf")) {
  language="fr";
}
 else if (host_tld.equals("bm")) {
  language="en";
}
 else if (host_tld.equals("bn")) {
  language="ms";
}
 else if (host_tld.equals("bb")) {
  language="en";
}
 else if (host_tld.equals("bt")) {
  language="dz";
}
 else if (host_tld.equals("bi")) {
  language="rn";
}
 else if (host_tld.equals("bs")) {
  language="en";
}
 else if (host_tld.equals("bj")) {
  language="fr";
}
 else if (host_tld.equals("bv")) {
  language="no";
}
break;
case 'c':
if (host_tld.equals("ca")) {
language="en";
}
 else if (host_tld.equals("ch")) {
language="de";
}
 else if (host_tld.equals("cn")) {
language="zh";
}
 else if (host_tld.equals("cz")) {
language="cs";
}
 else if (host_tld.equals("cl")) {
language="es";
}
 else if (host_tld.equals("co")) {
language="es";
}
 else if (host_tld.equals("cc")) {
language="en";
}
 else if (host_tld.equals("cr")) {
language="es";
}
 else if (host_tld.equals("cy")) {
language="el";
}
 else if (host_tld.equals("cu")) {
language="es";
}
 else if (host_tld.equals("cx")) {
language="en";
}
 else if (host_tld.equals("cd")) {
language="fr";
}
 else if (host_tld.equals("cg")) {
language="fr";
}
 else if (host_tld.equals("cm")) {
language="en";
}
 else if (host_tld.equals("ci")) {
language="fr";
}
 else if (host_tld.equals("cv")) {
language="pt";
}
 else if (host_tld.equals("ck")) {
language="en";
}
 else if (host_tld.equals("cf")) {
language="sg";
}
break;
case 'd':
if (host_tld.equals("dk")) {
language="da";
}
 else if (host_tld.equals("do")) {
language="es";
}
 else if (host_tld.equals("dz")) {
language="ar";
}
 else if (host_tld.equals("dj")) {
language="ar";
}
 else if (host_tld.equals("dm")) {
language="en";
}
break;
case 'e':
if (host_tld.equals("ee")) {
language="et";
}
 else if (host_tld.equals("eg")) {
language="ar";
}
 else if (host_tld.equals("ec")) {
language="es";
}
 else if (host_tld.equals("et")) {
language="am";
}
 else if (host_tld.equals("eu")) {
language="en";
}
 else if (host_tld.equals("er")) {
language="ti";
}
break;
case 'f':
if (host_tld.equals("fr")) {
language="fr";
}
 else if (host_tld.equals("fi")) {
language="fi";
}
 else if (host_tld.equals("fm")) {
language="en";
}
 else if (host_tld.equals("fo")) {
language="fo";
}
 else if (host_tld.equals("fj")) {
language="fj";
}
 else if (host_tld.equals("fk")) {
language="en";
}
break;
case 'g':
if (host_tld.equals("gr")) {
language="el";
}
 else if (host_tld.equals("ge")) {
language="ka";
}
 else if (host_tld.equals("gt")) {
language="es";
}
 else if (host_tld.equals("gs")) {
language="en";
}
 else if (host_tld.equals("gl")) {
language="kl";
}
 else if (host_tld.equals("gg")) {
language="en";
}
 else if (host_tld.equals("gi")) {
language="en";
}
 else if (host_tld.equals("gh")) {
language="en";
}
 else if (host_tld.equals("gy")) {
language="en";
}
 else if (host_tld.equals("gm")) {
language="en";
}
 else if (host_tld.equals("gn")) {
language="fr";
}
 else if (host_tld.equals("ga")) {
language="fr";
}
 else if (host_tld.equals("gd")) {
language="en";
}
 else if (host_tld.equals("gu")) {
language="en";
}
 else if (host_tld.equals("gq")) {
language="es";
}
 else if (host_tld.equals("gp")) {
language="fr";
}
 else if (host_tld.equals("gf")) {
language="fr";
}
 else if (host_tld.equals("gb")) {
language="en";
}
 else if (host_tld.equals("gw")) {
language="pt";
}
break;
case 'h':
if (host_tld.equals("hu")) {
language="hu";
}
 else if (host_tld.equals("hk")) {
language="zh";
}
 else if (host_tld.equals("hr")) {
language="hr";
}
 else if (host_tld.equals("hn")) {
language="es";
}
 else if (host_tld.equals("hm")) {
language="en";
}
 else if (host_tld.equals("ht")) {
language="fr";
}
break;
case 'i':
if (host_tld.equals("it")) {
language="it";
}
 else if (host_tld.equals("il")) {
language="he";
}
 else if (host_tld.equals("ie")) {
language="ga";
}
 else if (host_tld.equals("in")) {
language="hi";
}
 else if (language.equals("is")) {
language="is";
}
 else if (host_tld.equals("ir")) {
language="fa";
}
 else if (host_tld.equals("im")) {
language="en";
}
 else if (host_tld.equals("io")) {
language="en";
}
 else if (host_tld.equals("iq")) {
language="ar";
}
break;
case 'j':
if (host_tld.equals("jp")) {
language="ja";
}
 else if (host_tld.equals("jo")) {
language="ar";
}
 else if (host_tld.equals("jm")) {
language="en";
}
 else if (host_tld.equals("je")) {
language="en";
}
break;
case 'k':
if (host_tld.equals("kr")) {
language="ko";
}
 else if (host_tld.equals("kz")) {
language="kk";
}
 else if (host_tld.equals("kg")) {
language="ky";
}
 else if (host_tld.equals("ki")) {
language="en";
}
 else if (host_tld.equals("kw")) {
language="ar";
}
 else if (host_tld.equals("ke")) {
language="sw";
}
 else if (host_tld.equals("kh")) {
language="km";
}
 else if (host_tld.equals("ky")) {
language="en";
}
 else if (host_tld.equals("kn")) {
language="en";
}
 else if (host_tld.equals("km")) {
language="ar";
}
 else if (host_tld.equals("kp")) {
language="ko";
}
break;
case 'l':
if (host_tld.equals("lv")) {
language="lv";
}
 else if (host_tld.equals("lt")) {
language="lt";
}
 else if (host_tld.equals("lu")) {
language="lb";
}
 else if (host_tld.equals("li")) {
language="de";
}
 else if (host_tld.equals("lb")) {
language="ar";
}
 else if (host_tld.equals("lk")) {
language="si";
}
 else if (host_tld.equals("la")) {
language="lo";
}
 else if (host_tld.equals("ly")) {
language="ar";
}
 else if (host_tld.equals("lc")) {
language="en";
}
 else if (host_tld.equals("ls")) {
language="st";
}
 else if (host_tld.equals("lr")) {
language="en";
}
break;
case 'm':
if (host_tld.equals("mx")) {
language="es";
}
 else if (host_tld.equals("my")) {
language="en";
}
 else if (host_tld.equals("md")) {
language="ro";
}
 else if (host_tld.equals("ma")) {
language="ar";
}
 else if (host_tld.equals("mk")) {
language="mk";
}
 else if (host_tld.equals("ms")) {
language="en";
}
 else if (host_tld.equals("mt")) {
language="mt";
}
 else if (host_tld.equals("mo")) {
language="zh";
}
 else if (host_tld.equals("mn")) {
language="mn";
}
 else if (host_tld.equals("mp")) {
language="en";
}
 else if (host_tld.equals("mu")) {
language="fr";
}
 else if (host_tld.equals("mm")) {
language="my";
}
 else if (host_tld.equals("mc")) {
language="fr";
}
 else if (host_tld.equals("me")) {
language="sh";
}
 else if (host_tld.equals("mz")) {
language="pt";
}
 else if (host_tld.equals("mg")) {
language="mg";
}
 else if (host_tld.equals("mr")) {
language="ar";
}
 else if (host_tld.equals("mv")) {
language="dv";
}
 else if (host_tld.equals("mw")) {
language="en";
}
 else if (host_tld.equals("ml")) {
language="fr";
}
 else if (host_tld.equals("mq")) {
language="fr";
}
 else if (host_tld.equals("mh")) {
language="mh";
}
break;
case 'n':
if (host_tld.equals("no")) {
language="no";
}
 else if (host_tld.equals("nz")) {
language="en";
}
 else if (host_tld.equals("nu")) {
language="en";
}
 else if (host_tld.equals("ni")) {
language="es";
}
 else if (host_tld.equals("np")) {
language="ne";
}
if (host_tld.equals("na")) {
language="af";
}
 else if (host_tld.equals("nr")) {
language="en";
}
 else if (host_tld.equals("nc")) {
language="fr";
}
 else if (host_tld.equals("ne")) {
language="fr";
}
 else if (host_tld.equals("ng")) {
language="en";
}
 else if (host_tld.equals("nf")) {
language="en";
}
break;
case 'o':
if (host_tld.equals("om")) {
language="ar";
}
break;
case 'p':
if (host_tld.equals("pl")) {
language="pl";
}
 else if (host_tld.equals("pt")) {
language="pt";
}
 else if (host_tld.equals("ph")) {
language="tl";
}
 else if (host_tld.equals("pk")) {
language="ur";
}
 else if (host_tld.equals("pw")) {
language="en";
}
 else if (host_tld.equals("pe")) {
language="es";
}
 else if (host_tld.equals("pr")) {
language="es";
}
 else if (host_tld.equals("pa")) {
language="es";
}
 else if (host_tld.equals("py")) {
language="gn";
}
 else if (host_tld.equals("ps")) {
language="ar";
}
 else if (host_tld.equals("pf")) {
language="fr";
}
 else if (host_tld.equals("pg")) {
language="en";
}
 else if (host_tld.equals("pn")) {
language="en";
}
 else if (host_tld.equals("pm")) {
language="fr";
}
break;
case 'q':
if (host_tld.equals("qa")) {
language="ar";
}
break;
case 'r':
if (host_tld.equals("ru")) {
language="ru";
}
 else if (host_tld.equals("ro")) {
language="ro";
}
 else if (host_tld.equals("rs")) {
language="sr";
}
 else if (host_tld.equals("re")) {
language="fr";
}
 else if (host_tld.equals("rw")) {
language="rw";
}
break;
case 's':
if (host_tld.equals("se")) {
language="sv";
}
 else if (host_tld.equals("es")) {
language="es";
}
 else if (host_tld.equals("sg")) {
language="zh";
}
 else if (host_tld.equals("sk")) {
language="sk";
}
 else if (host_tld.equals("si")) {
language="sl";
}
 else if (host_tld.equals("su")) {
language="ru";
}
 else if (host_tld.equals("sa")) {
language="ar";
}
 else if (host_tld.equals("st")) {
language="pt";
}
 else if (host_tld.equals("sv")) {
language="es";
}
 else if (host_tld.equals("sc")) {
language="en";
}
 else if (host_tld.equals("sh")) {
language="en";
}
 else if (host_tld.equals("sn")) {
language="wo";
}
 else if (host_tld.equals("sr")) {
language="nl";
}
 else if (host_tld.equals("sm")) {
language="it";
}
 else if (host_tld.equals("sy")) {
language="ar";
}
 else if (host_tld.equals("sz")) {
language="ss";
}
 else if (host_tld.equals("sl")) {
language="en";
}
 else if (host_tld.equals("sb")) {
language="en";
}
 else if (host_tld.equals("sd")) {
language="ar";
}
 else if (host_tld.equals("so")) {
language="so";
}
 else if (host_tld.equals("ss")) {
language="en";
}
break;
case 't':
if (host_tld.equals("tw")) {
language="zh";
}
 else if (host_tld.equals("tr")) {
language="tr";
}
 else if (host_tld.equals("tv")) {
language="en";
}
 else if (host_tld.equals("th")) {
language="th";
}
 else if (host_tld.equals("tc")) {
language="en";
}
 else if (host_tld.equals("to")) {
language="to";
}
 else if (host_tld.equals("tk")) {
language="to";
}
 else if (host_tld.equals("tt")) {
language="en";
}
 else if (host_tld.equals("tn")) {
language="ar";
}
 else if (host_tld.equals("tf")) {
language="fr";
}
 else if (host_tld.equals("tz")) {
language="sw";
}
 else if (host_tld.equals("tj")) {
language="tg";
}
 else if (host_tld.equals("tp")) {
language="pt";
}
 else if (host_tld.equals("tm")) {
language="tk";
}
 else if (host_tld.equals("tg")) {
language="fr";
}
 else if (host_tld.equals("tl")) {
language="id";
}
 else if (host_tld.equals("td")) {
language="ar";
}
break;
case 'u':
if (host_tld.equals("uk")) {
language="en";
}
 else if (host_tld.equals("us")) {
language="en";
}
 else if (host_tld.equals("ua")) {
language="uk";
}
 else if (host_tld.equals("uz")) {
language="uz";
}
 else if (host_tld.equals("uy")) {
language="es";
}
 else if (host_tld.equals("ug")) {
language="sw";
}
break;
case 'v':
if (host_tld.equals("vu")) {
language="en";
}
 else if (host_tld.equals("ve")) {
language="es";
}
 else if (host_tld.equals("vn")) {
language="vi";
}
 else if (host_tld.equals("va")) {
language="it";
}
 else if (host_tld.equals("vg")) {
language="en";
}
 else if (host_tld.equals("vc")) {
language="en";
}
 else if (host_tld.equals("vi")) {
language="en";
}
break;
case 'w':
if (host_tld.equals("ws")) {
language="sm";
}
 else if (host_tld.equals("wf")) {
language="fr";
}
break;
case 'x':
break;
case 'y':
if (host_tld.equals("yu")) {
language="sh";
}
 else if (host_tld.equals("ye")) {
language="ar";
}
 else if (host_tld.equals("yt")) {
language="fr";
}
break;
case 'z':
if (host_tld.equals("za")) {
language="af";
}
 else if (host_tld.equals("zw")) {
language="sn";
}
 else if (host_tld.equals("zm")) {
language="en";
}
break;
}
break;
case 3:
if (host_tld.equals("cat")) {
language="ca";
}
break;
case 8:
if (host_tld.equals("xn--p1ai")) {
language="ru";
}
 else if (host_tld.equals("xn--node")) {
language="ka";
}
break;
case 9:
if (host_tld.equals("xn--j1amh")) {
language="uk";
}
break;
case 10:
if (host_tld.equals("xn--fiqs8s")) {
language="zh";
}
 else if (host_tld.equals("xn--fiqz9s")) {
language="zh";
}
 else if (host_tld.equals("xn--o3cw4h")) {
language="th";
}
 else if (host_tld.equals("xn--wgbh1c")) {
language="ar";
}
 else if (host_tld.equals("xn--wgbl6a")) {
language="ar";
}
 else if (host_tld.equals("xn--90a3ac")) {
language="sr";
}
 else if (host_tld.equals("xn--wgv71a")) {
language="ja";
}
break;
case 11:
if (host_tld.equals("xn--kprw13d")) {
language="zh";
}
 else if (host_tld.equals("xn--kpry57d")) {
language="zh";
}
 else if (host_tld.equals("xn--j6w193g")) {
language="zh";
}
 else if (host_tld.equals("xn--h2brj9c")) {
language="hi";
}
 else if (host_tld.equals("xn--gecrj9c")) {
language="gu";
}
 else if (host_tld.equals("xn--s9brj9c")) {
language="pa";
}
 else if (host_tld.equals("xn--45brj9c")) {
language="bn";
}
 else if (host_tld.equals("xn--pgbs0dh")) {
language="ar";
}
 else if (host_tld.equals("xn--80ao21a")) {
language="kk";
}
break;
case 12:
if (host_tld.equals("xn--3e0b707e")) {
language="ko";
}
 else if (host_tld.equals("xn--mgbtf8fl")) {
language="ar";
}
 else if (host_tld.equals("xn--4dbrk0ce")) {
language="he";
}
 else if (host_tld.equals("xn--mgb9awbf")) {
language="ar";
}
 else if (host_tld.equals("xn--mgb2ddes")) {
language="ar";
}
break;
case 13:
if (host_tld.equals("xn--fpcrj9c3d")) {
language="te";
}
 else if (host_tld.equals("xn--yfro4i67o")) {
language="zh";
}
 else if (host_tld.equals("xn--fzc2c9e2c")) {
language="si";
}
 else if (host_tld.equals("xn--ygbi2ammx")) {
language="ar";
}
break;
case 14:
if (host_tld.equals("xn--mgbbh1a71e")) {
language="ur";
}
 else if (host_tld.equals("xn--mgbaam7a8h")) {
language="ar";
}
 else if (host_tld.equals("xn--mgbayh7gpa")) {
language="ar";
}
 else if (host_tld.equals("xn--mgbx4cd0ab")) {
language="ar";
}
 else if (host_tld.equals("xn--54b7fta0cc")) {
language="bn";
}
break;
case 15:
if (host_tld.equals("xn--mgbc0a9azcg")) {
language="ar";
}
 else if (host_tld.equals("xn--mgba3a4f16a")) {
language="fa";
}
 else if (host_tld.equals("xn--lgbbat1ad8j")) {
language="ar";
}
break;
case 16:
if (host_tld.equals("xn--xkc2al3hye2a")) {
language="ta";
}
break;
case 17:
if (host_tld.equals("xn--xkc2dl3a5ee0h")) {
language="ta";
}
 else if (host_tld.equals("xn--mgberp4a5d4ar")) {
language="ar";
}
 else if (host_tld.equals("xn--mgbai9azgqp6j")) {
language="ar";
}
break;
case 22:
if (host_tld.equals("xn--clchc0ea0b2g2a9gcd")) {
language="ta";
}
break;
default :
break;
}
return language;
}
