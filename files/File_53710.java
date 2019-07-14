package com.lingjoin.demo;

/**
 * 分�?组件方法类
 * 
 * @author move
 *
 */
public class NlpirMethod {
	// �?始化
	static {
		boolean flag = NlpirLib.Instance.NLPIR_Init("", 1, "");

		if (flag) {
			System.out.println("nlpir�?始化�?功");
		} else {
			System.out.println("nlpir�?始化失败：" + NlpirLib.Instance.NLPIR_GetLastErrorMsg());
			System.exit(1);
		}
	}

	/**
	 * 组件�?始化
	 * 
	 * @param sDataPath
	 *            Data文件夹的父目录，如果为空字符串（�?�：""），那么，程�?自动从项目的根目录中寻找
	 * @param encoding
	 *            编�?格�?，具体的编�?对照如下： 0：GBK；1：UTF8；2：BIG5；3：GBK，里�?�包�?��?体字
	 * @param sLicenceCode
	 *            授�?��?，为空字符串（�?�：""）就�?�以了
	 * @return true：�?始化�?功；false：�?始化失败
	 */
	public static boolean NLPIR_Init(String sDataPath, int encoding, String sLicenceCode) {
		return NlpirLib.Instance.NLPIR_Init(sDataPath, encoding, sLicenceCode);
	}

	/**
	 * 分�?
	 * 
	 * @param sSrc
	 *            文本内容
	 * @param bPOSTagged
	 *            1：显示�?性；0：�?显示�?性
	 * @return 分�?结果
	 */
	public static String NLPIR_ParagraphProcess(String sParagraph, int bPOSTagged) {
		return NlpirLib.Instance.NLPIR_ParagraphProcess(sParagraph, bPOSTagged);
	}

	/**
	 * 分�?
	 * 
	 * @param sSourceFilename
	 *            文本文件的路径
	 * @param sResultFilename
	 *            结果文件的路径
	 * @param bPOStagged
	 *            1：显示�?性；0：�?显示�?性
	 * @return
	 */
	public static double NLPIR_FileProcess(String sSourceFilename, String sResultFilename, int bPOStagged) {
		return NlpirLib.Instance.NLPIR_FileProcess(sSourceFilename, sResultFilename, bPOStagged);
	}

	/**
	 * 细粒度分�?
	 * 
	 * @param lenWords
	 *            文本内容
	 * @return 分�?结果
	 */
	public static String NLPIR_FinerSegment(String lenWords) {
		return NlpirLib.Instance.NLPIR_FinerSegment(lenWords);
	}

	/**
	 * 关键�?
	 * 
	 * @param sLine
	 *            文本内容
	 * @param nMaxKeyLimit
	 *            生�?关键�?的个数上�?
	 * @param bWeightOut
	 *            true：显示�?性；false：�?显示�?性
	 * @return 关键�?组�?的字符串 备注：黑�??�?�中出现的�?，�?会作为关键�?出现
	 */
	public static String NLPIR_GetKeyWords(String sLine, int nMaxKeyLimit, boolean bWeightOut) {
		return NlpirLib.Instance.NLPIR_GetKeyWords(sLine, nMaxKeyLimit, bWeightOut);
	}

	/**
	 * 关键�?
	 * 
	 * @param sFilename
	 *            文本文件的路径
	 * @param nMaxKeyLimit
	 *            生�?的关键�?的个数上�?
	 * @param bWeightOut
	 *            true：显示�?性；false：�?显示�?性
	 * @return 关键�?组�?的字符串 备注：黑�??�?�中出现的�?，�?会作为关键�?出现
	 */
	public static String NLPIR_GetFileKeyWords(String sFilename, int nMaxKeyLimit, boolean bWeightOut) {
		return NlpirLib.Instance.NLPIR_GetFileKeyWords(sFilename, nMaxKeyLimit, bWeightOut);
	}

	/**
	 * 新�?
	 * 
	 * @param sLine
	 *            文本内容
	 * @param nMaxKeyLimit
	 *            生�?的新�?的个数上�?
	 * @param bWeightOut
	 *            true：显示�?性；false：�?显示�?性
	 * @return 新�?组�?的字符串
	 */
	public static String NLPIR_GetNewWords(String sLine, int nMaxKeyLimit, boolean bWeightOut) {
		return NlpirLib.Instance.NLPIR_GetNewWords(sLine, nMaxKeyLimit, bWeightOut);
	}

	/**
	 * 新�?
	 * 
	 * @param string
	 *            文本文件的路径
	 * @param nMaxKeyLimit
	 *            生�?的新�?的个数上�?
	 * @param bWeightOut
	 *            true:显示�?性信�?�；false:�?显示�?性信�?�
	 * @return 新�?组�?的字符串
	 */
	public static String NLPIR_GetFileNewWords(String sFilename, int nMaxKeyLimit, boolean bWeightOut) {
		return NlpirLib.Instance.NLPIR_GetFileNewWords(sFilename, nMaxKeyLimit, bWeightOut);
	}

	/**
	 * 添加用户自定义�?
	 * 
	 * @param userWord
	 *            用户�? 格�?：�?��?+空格+�?性，例如：你好 v
	 * @return 1：内存中�?存在；2：内存中已存在 备注：�?存到内存中，下次�?始化�?�失效，需�?用save�?存到文件中
	 */
	public static int NLPIR_AddUserWord(String userWord) {
		return NlpirLib.Instance.NLPIR_AddUserWord(userWord);
	}

	/**
	 * �?存用户自定义�?(�?存到文件中)
	 * 
	 * @return 1：�?功；0：失败
	 */
	public static int NLPIR_SaveTheUsrDic() {
		return NlpirLib.Instance.NLPIR_SaveTheUsrDic();
	}

	/**
	 * 删除用户自定义�?
	 * 
	 * @param sWord
	 *            需�?删除的�?��?
	 * @return 被删除�?��?在内存中的�?置，-1表示�?存在 备注：删除内存中的自定义�?，下次�?始化�?�失效，需�?用save�?存到文件中
	 */
	public static int NLPIR_DelUsrWord(String sWord) {
		return NlpirLib.Instance.NLPIR_DelUsrWord(sWord);
	}

	/**
	 * 导入用户自定义�?典
	 * 
	 * @param dictFileName
	 *            用户�?典的路径
	 * @param bOverwrite
	 *            是�?�删除原有的自定义用户�?典，true：删除；false：�?删除
	 * @return 导入用户�?��?个数 备注：系统会自动处�?��?�?�?的问题
	 */
	public static int NLPIR_ImportUserDict(String dictFileName, boolean bOverwrite) {
		return NlpirLib.Instance.NLPIR_ImportUserDict(dictFileName, bOverwrite);
	}

	/**
	 * 导入关键�?黑�??�?�
	 * 
	 * @param sFilename
	 *            文件的路径
	 * @return 备注：�?功导入�?�，黑�??�?�中出现的�?，�?会作为关键�?出现
	 */
	public static int NLPIR_ImportKeyBlackList(String sFilename) {
		return NlpirLib.Instance.NLPIR_ImportKeyBlackList(sFilename);
	}

	/**
	 * 文章指纹�?
	 * 
	 * @param sLine
	 *            文本内容
	 * @return 指纹�?
	 */
	public static long NLPIR_FingerPrint(String sLine) {
		return NlpirLib.Instance.NLPIR_FingerPrint(sLine);
	}

	/**
	 * �?��?的�?性
	 * 
	 * @param sWords
	 *            �?��?，例如：中�?�人民共和国
	 * @return �?��?的�?性，例如：中�?�人民共和国/ns/607#
	 */
	public static String NLPIR_GetWordPOS(String sWords) {
		return NlpirLib.Instance.NLPIR_GetWordPOS(sWords);
	}

	/**
	 * 判断�?��?是�?�在核心�?库中
	 * 
	 * @param word
	 *            输入的�?��?
	 * @return 如果�?��?�?存在就返回-1，�?�则返回�?��?在�?典中的�?�柄
	 */
	public static int NLPIR_IsWord(String word) {
		return NlpirLib.Instance.NLPIR_IsWord(word);
	}

	/**
	 * 获�?�输入文本的�?，�?性，频统计结果，按照�?频大�?排�?
	 * 
	 * @param sText
	 *            文本内容
	 * @return �?频统计结果形�?如下：张�?�平/nr/10#�?�士/n/9#分�?/n/8
	 */
	public static String NLPIR_WordFreqStat(String sText) {
		return NlpirLib.Instance.NLPIR_WordFreqStat(sText);
	}

	/**
	 * 获�?�输入文本文件的�?，�?性，频统计结果，按照�?频大�?排�?
	 * 
	 * @param sFilename
	 *            文本文件的全路径
	 * @return �?频统计结果形�?如下：张�?�平/nr/10#�?�士/n/9#分�?/n/8
	 */
	public static String NLPIR_FileWordFreqStat(String sFilename) {
		return NlpirLib.Instance.NLPIR_FileWordFreqStat(sFilename);
	}

	/**
	 * 获�?��?�类英文�?��?的原型，考虑了过去分�?�?�?��?数等情况
	 * 
	 * @param sWord
	 *            输入的�?��?
	 * @return �?原型形�?，例如：driven->drive drives->drive drove-->drive
	 */
	public static String NLPIR_GetEngWordOrign(String sWord) {
		return NlpirLib.Instance.NLPIR_GetEngWordOrign(sWord);
	}

	/**
	 * 返回最�?�一次的出错信�?�
	 * 
	 * @return 最�?�一次的出错信�?�
	 */
	public static String NLPIR_GetLastErrorMsg() {
		return NlpirLib.Instance.NLPIR_GetLastErrorMsg();
	}

	/**
	 * 退出，释放资�?
	 * 
	 * @return
	 */
	public static boolean NLPIR_Exit() {
		return NlpirLib.Instance.NLPIR_Exit();
	}
}
