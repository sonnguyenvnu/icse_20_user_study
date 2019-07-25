package cn.crap.service.tool;

import cn.crap.dto.SearchDto;
import cn.crap.enu.MyError;
import cn.crap.framework.MyException;
import cn.crap.query.SearchQuery;
import cn.crap.service.ILuceneService;
import cn.crap.service.ISearchService;
import cn.crap.utils.*;
import org.apache.log4j.Logger;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.*;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.search.highlight.*;
import org.apache.lucene.search.highlight.Scorer;
import org.apache.lucene.store.FSDirectory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * TODO 待解决的问题
 * debug �?端�?�索没有展示页
 * �?�索地�?�尚未调试
 */
@Service("luceneSearch")
public class LuceneSearchService implements ISearchService {
	protected Logger log = Logger.getLogger(getClass());

	@Autowired
	private SettingCache settingCache;
	@Autowired
	private StringCache stringCache;
	@Autowired
    private ProjectCache projectCache;
	/**
	 * 在默认情况下使用 @Autowired 注释进行自动注入时，Spring 容器中匹�?的候选 Bean 数目必须有且仅有一个
	 * @Autowired(required = false)，这等于告诉 Spring：在找�?到匹�? Bean 时也�?报错
	 */
	@Autowired(required=false)
	private ILuceneService[] luceneServices;

	private final static String TITLE = "title";
	private final static String PROJECT_ID = "projectId";
	private final static String MODULE_ID = "moduleId";
	private final static String ID = "id";
	private final static String TABLE_ID = "tableID";
	private final static String CONTENT = "content";
	private final static String OPEN = "open";
	private final static String CUSTOM = "custom";
	private final static String CREATE_TIME = "createTime";

	/**
	 * h_开头表示高亮
	 */
	private final static String HIGH_LIGHT_PRE = "h_";
	private final static String H_CONTENT = HIGH_LIGHT_PRE + CONTENT;
	private final static String H_TITLE = HIGH_LIGHT_PRE + TITLE;

	private final int PAGE_SIZE = 200;

	@Override
	public List<SearchDto> search(SearchQuery searchQuery) throws Exception {
		String keyword = handleHref(searchQuery.getKeyword());
		if (MyString.isEmpty(keyword)) {
			return new ArrayList<>();
		}

		IndexReader reader = null;
		try {
			reader = DirectoryReader.open(FSDirectory.open(Paths.get(settingCache.get(ISetting.S_LUCENE_DIR).getValue())));
			IndexSearcher searcher = new IndexSearcher(reader);
			Analyzer analyzer = new StandardAnalyzer();
			String[] fields = {ID, CUSTOM, CONTENT, TITLE};

			QueryParser parser = new MultiFieldQueryParser(fields, analyzer);
			Query keywordQuery = parser.parse(keyword);

			// Sort sort = new Sort();
			// sortField = "relativeScore";
			// if(!sortField.equals(Constant.RELATIVE_SCORE)){
			// sort.setSort(new SortField(sortField,false)); // 默认为�?��?，修改为�?�?
			// }
            BooleanClause keywordClause = new BooleanClause(keywordQuery, BooleanClause.Occur.MUST);
            BooleanQuery.Builder boolBuilder = new BooleanQuery.Builder().add(keywordClause);

			/**
			 * TODO 一期暂时�?�能支�?项目下�?或公开项目�?�索
             * null: 代表查询有所有开放的，或有�?��?的：暂�?支�?
			 * true : 表示�?�能查询开放�?�索的项目
			 * false : 代表查询有所有有�?��?的
			 */
            Boolean open = searchQuery.getOpen();
            // 查询全部�?或有�?��?的数�?�，�?�时项目ID为空，则必须校验�?��?
            if ( (open == null || !open) && searchQuery.getProjectId() == null){
				throw new MyException(MyError.E000056);
            }

			if (open != null){
				boolBuilder.add(new BooleanClause(new TermQuery(new Term(OPEN, open + "")), BooleanClause.Occur.MUST));
			}
			if (searchQuery.getProjectId() != null){
				boolBuilder.add(new BooleanClause(new TermQuery(new Term(PROJECT_ID, searchQuery.getProjectId())), BooleanClause.Occur.MUST));
			}


            BooleanQuery query = boolBuilder.build();
            TopDocs topDocs = searcher.search(query, 1000);
			List<SearchDto> searchDtos = new ArrayList<>();

			// ============== 准备高亮器
			Formatter formatter = new SimpleHTMLFormatter("<font color='red'>", "</font>");
			Scorer scorer = new QueryScorer(keywordQuery);
			Highlighter highlighter = new Highlighter(formatter, scorer);

			// 设置最长的 读�?�数�?
			Fragmenter fragmenter = new SimpleFragmenter(300);
			highlighter.setTextFragmenter(fragmenter);

			// �?�出当�?页的数�?�
			searchQuery.setAllRow(topDocs.totalHits);
			if (searchQuery.getCurrentPage() > searchQuery.getTotalPage()){
			    return new ArrayList<>();
            }

			int end = Math.min(searchQuery.getStart() + searchQuery.getPageSize(), topDocs.totalHits);
			for (int i = searchQuery.getStart(); i < end; i++) {
				ScoreDoc scoreDoc = topDocs.scoreDocs[i];
				float relativeScore = scoreDoc.score;
				int docSn = scoreDoc.doc; // 文档内部编�?�
				Document doc = searcher.doc(docSn); // 根�?�编�?��?�出相应的文档
				doc.add(new StringField("relativeScore", relativeScore + "", Field.Store.NO));

				addHighLightField(highlighter, doc, TITLE);
				addHighLightField(highlighter, doc, CONTENT);

				searchDtos.add(docToDto(doc));
			}
			return searchDtos;
		} catch (Exception e) {
			e.printStackTrace();
			stringCache.add(IConst.C_CACHE_ERROR_TIP, "Lucene�?�索异常，请�?�系管�?�员查看日志，错误信�?�：" + e.getMessage());
		} finally {
			if (reader != null)
				reader.close();
		}
		return new ArrayList<>();
	}

	@Override
	public boolean delete(SearchDto searchDto) throws IOException {
		IndexWriter writer = null;
		try {
			IndexWriterConfig conf = new IndexWriterConfig(new StandardAnalyzer());
			conf.setOpenMode(OpenMode.CREATE_OR_APPEND);
			writer = new IndexWriter(FSDirectory.open(Paths.get(settingCache.get(ISetting.S_LUCENE_DIR).getValue())), conf);
			writer.deleteDocuments(new Term(ID, searchDto.getId()));
		} catch (Exception e) {
			e.printStackTrace();
			stringCache.add(IConst.C_CACHE_ERROR_TIP, "Lucene删除异常，请�?�系管�?�员查看日志，错误信�?�：" + e.getMessage());
		} finally {
			if (writer != null) {
				writer.close();
			}
		}
		return true;
	}



	private SearchDto docToDto(Document doc) {
		SearchDto dto = new SearchDto();
		// 高亮处�?�的�?�索结果
		dto.setContent(doc.get(H_CONTENT));
		dto.setCreateTime(doc.get(CREATE_TIME) == null? null : new Date(Long.parseLong(doc.get(CREATE_TIME))));
		// �?��?�??斜�?�
		dto.setCustom(unHandleHref(doc.get(CUSTOM)));
		dto.setId(doc.get(ID));
		dto.setTitle(doc.get(H_TITLE));
		dto.setTableId(doc.get(TABLE_ID));
		dto.setProjectId(doc.get(PROJECT_ID));
		dto.setModuleId(doc.get(MODULE_ID));
		dto.setOpen(Boolean.parseBoolean(doc.get(OPEN)));
        dto.setProjectName(projectCache.getName(dto.getProjectId()));
        dto.setUserHref(UseHrefUtil.getUserHref(dto));
        dto.setHref(UseHrefUtil.getHref(dto));
        dto.setCreateTimeStr(DateFormartUtil.getDateByTimeMillis(dto.getCreateTime()));
		return dto;
	}

	private static Document dtoToDoc(SearchDto dto) {
		Document doc = new Document();

		// Add the path of the file as a field named "path". Use a
		// field that is indexed (i.e. searchable), but don't tokenize
		// the field into separate words and don't index term frequency
		// or positional information:
		doc.add(new StringField(ID, dto.getId(), Field.Store.YES));
		doc.add(new StringField(CREATE_TIME, dto.getCreateTime() == null ? System.currentTimeMillis() + "" : dto.getCreateTime().getTime() + "", Field.Store.YES));
		doc.add(new StringField(PROJECT_ID, dto.getProjectId(), Field.Store.YES));
		doc.add(new StringField(MODULE_ID, dto.getModuleId(), Field.Store.YES));
		doc.add(new StringField(TABLE_ID, dto.getTableId(), Field.Store.YES));
		doc.add(new StringField((OPEN), dto.isOpen() + "", Field.Store.YES));
		doc.add(new TextField(CONTENT, Tools.removeHtml(dto.getContent()), Field.Store.YES));
		doc.add(new TextField(TITLE, dto.getTitle(), Field.Store.YES));
		// doc.add(new StringField(CUSTOM, handleHref(dto.getCustom()), Field.Store.YES));
		doc.add(new TextField(CUSTOM, dto.getCustom(), Field.Store.YES));

		return doc;
	}

	/**
	 * 为�?个属性设置高亮�?作
	 *
	 * @param highlighter
	 *            高亮器
	 * @param doc
	 *            文本
	 * @param fieldName
	 *            属性�??字
	 * @throws Exception
	 */
	private static void addHighLightField(Highlighter highlighter, Document doc, String fieldName) throws Exception {
		String fieldValue = doc.get(fieldName);
		String hc = null;
		if (fieldValue != null) {
			hc = highlighter.getBestFragment(new StandardAnalyzer(), fieldName,  fieldValue.length() > 200 ? fieldValue.substring(0, 200) + "..." : fieldValue);
		}

		if (hc == null) {
			if (fieldValue != null) {
				hc = fieldValue.substring(0, Math.min(50, fieldValue.length()));
			} else {
				hc = "";
			}
		}
		doc.add(new StringField(HIGH_LIGHT_PRE + fieldName, hc, Field.Store.YES));
	}

	@Override
	public boolean add(SearchDto searchDto){
		IndexWriter writer = null;
		try {
			IndexWriterConfig conf = new IndexWriterConfig(new StandardAnalyzer());
			conf.setOpenMode(OpenMode.CREATE_OR_APPEND);
			writer = new IndexWriter(FSDirectory.open(Paths.get(settingCache.get(ISetting.S_LUCENE_DIR).getValue())), conf);
			writer.updateDocument(new Term("id", searchDto.getId()), dtoToDoc(searchDto));
		} catch (Exception e) {
			e.printStackTrace();
			stringCache.add(IConst.C_CACHE_ERROR_TIP, "Lucene异常，请�?�系管�?�员查看日志，错误信�?�（消�?�将�?留10分钟，请�?�时处�?�）：" + e.getMessage());
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return true;
	}

	@Override
	public boolean update(SearchDto searchDto) throws IOException {
		return add(searchDto);
	}

	private static volatile boolean isRebuild = false;

	/**
	 * �?建系统索引
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean rebuildByProjectId(String projectId){
		if(isRebuild){
			return true;
		}
		synchronized (LuceneSearchService.this) {
			try{
				isRebuild = true;
                /**
                 * 全部�?建时需�?删除原�?�的索引文件
                 */
				if (projectId == null){
                    File file = new File(settingCache.get(ISetting.S_LUCENE_DIR).getValue());
                    File[] tempList = file.listFiles();
                    for (int i = 0; i < tempList.length; i++) {
                        tempList[i].delete();
                    }
                }

			    for(ILuceneService service:luceneServices){
					log.error("正在创建索引--------------" + service.getClass());
			    	int i = 0;
			    	String id = null;

			    	while (true){
                        List<SearchDto> dtos= service.selectOrderById(projectId, id, PAGE_SIZE);
                        for (SearchDto dto : dtos) {
                            i++;
                            if (projectId == null){
                                stringCache.add(IConst.C_CACHE_ERROR_TIP, service.getClass() + "当�?正在创建第"+i+"�?�记录");
                            }
                            // �?��?�?�用太大的系统资�?
                            try {
                                Thread.sleep(50);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            add(dto);
                            id = dto.getId();
                        }
                        if (CollectionUtils.isEmpty(dtos) || dtos.size() != PAGE_SIZE){
                            break;
                        }
                    }
                    log.error("建索引创建完�?-----------" + service.getClass());
			    }
                if (projectId == null) {
                    stringCache.add(IConst.C_CACHE_ERROR_TIP, "�?建索引�?功�?");
                }
			}catch(Throwable e){
				log.error("建索引创建异常----------", e);
				e.printStackTrace();
			}finally{
				isRebuild = false;
			}

		}
	    return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean rebuild(){
		return rebuildByProjectId(null);
	}

	public static String handleHref(String href){
		if(href == null) {
			return "";
		}
		// + – && || ! ( ) { } [ ] ^ �? ~ * ? : /
//		return href.replaceAll("\\/", "ca_xg").replaceAll("\\+", "ca_add")
//                .replaceAll("\\-", "ca_des").replaceAll("\\&", "ca_and")
//				.replaceAll("\\|", "ca_xhx").replaceAll("\\{", "ca_dkhs")
//                .replaceAll("\\}", "ca_dkhe").replaceAll("\\?", "ca_wh")
//				.replaceAll("\\*", "ca_xh").replaceAll("\\@", "ca_at")
//                .replaceAll("\\:", "ca_mh").replaceAll("\\.", "ca_dh");
        return href;
	}
	public static String unHandleHref(String href){
		if(href == null) {
			return "";
		}
		// + – && || ! ( ) { } [ ] ^ �? ~ * ? : /
//		return href.replaceAll("ca_xg", "\\/").replaceAll( "ca_add","\\+")
//                .replaceAll( "ca_des", "\\-").replaceAll( "ca_and","\\&")
//				.replaceAll("ca_xhx", "\\|").replaceAll("ca_dkhs", "\\{")
//                .replaceAll("ca_dkhe","\\}").replaceAll( "ca_wh","\\?")
//				.replaceAll("ca_xh", "\\*").replaceAll("ca_at", "\\@")
//                .replaceAll("ca_mh", "\\:").replaceAll("ca_dh", "\\.");
        return href;
	}
}
