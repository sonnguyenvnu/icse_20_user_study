package io.xream.x7.demo.controller;

import io.xream.x7.demo.*;
import io.xream.x7.demo.bean.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import x7.core.bean.*;
import x7.core.bean.condition.RefreshCondition;
import x7.core.util.JsonX;
import x7.core.web.Direction;
import x7.core.web.Page;
import x7.core.web.ViewEntity;

import java.util.*;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/xxx")
//@Transactional
public class XxxController {

	@Autowired
	private CatTestRepository repository;// sample

	@Autowired
	private CatRepository catRepository;// sample

	@Autowired
	private PigRepository pigRepository;

	@Autowired
	private TimeJackRepository timeJackRepository;


	@RequestMapping("/create")
//	@Transactional
	public ViewEntity create(){

		Cat cat = new Cat();
		cat.setId(245);
		cat.setDogId(2);

//		this.catRepository.create(cat);

		Cat cat2 = new Cat();
		cat2.setId(246);
		cat2.setDogId(2);
//
//		this.catRepository.create(cat2);

		List<Cat> catList = new ArrayList<>();
		for (int i = 0; i< 500; i++){
			Cat cat3 = new Cat();
			cat3.setId(5448 + i);
			cat3.setDogId(2);
			catList.add(cat3);
		}
		this.catRepository.createBatch(catList);
//		throw new RuntimeException("-----------------------------> test wawawawa");

		return ViewEntity.ok();
	}

	@RequestMapping("/refresh")
	public ViewEntity refreshByCondition(@RequestBody Cat cat){


//		System.out.println("______: "  + test);
//		CriteriaBuilder builder = CriteriaBuilder.buildCondition();
//		builder.and().eq("type","NL");
//		builder.and().eq("id",2);
//
//		CriteriaCondition criteriaCondition = builder.get();

//		this.catRepository.create(cat);

		RefreshCondition<Cat> refreshCondition = new RefreshCondition<Cat>(null);
		refreshCondition.and().eq("type","NL");
		//refreshCondition.refresh("test=test+1");//表达�?更新
		refreshCondition.refresh("test",3333).refresh("type","XL");//赋值更新
		this.catRepository.refresh(refreshCondition);//必须带ID更新，没ID报错
		this.catRepository.refreshUnSafe(refreshCondition);//�?�以多�?�更新

//		if (true){
//			throw new RuntimeException("xxxxxxxxxxxxxxxxxxxx");
//		}

		return ViewEntity.ok();
	}


	@RequestMapping("/distinct")
	public ViewEntity distinct(@RequestBody CatRO ro) {

		CriteriaBuilder.ResultMappedBuilder builder = CriteriaBuilder.buildResultMapped(CatTest.class,ro);
		builder.distinct("catTest.dogId").distinct("catTest.catFriendName")
				.reduce(Reduce.ReduceType.COUNT,"catTest.id")
				.reduce(Reduce.ReduceType.SUM, "catTest.id")
				.groupBy("catTest.dogId")
				.groupBy("catTest.catFriendName")
		.paged().page(1).rows(2).sort("catTest.dogId",Direction.DESC);
		String sourceScript = "catTest ";
		Criteria.ResultMappedCriteria resultMapped = builder.get();
		resultMapped.setSourceScript(sourceScript);
		Page<Map<String,Object>> page = repository.find(resultMapped);

		return ViewEntity.ok(page);
	}

	@RequestMapping("/test")
	public ViewEntity test(@RequestBody CatRO ro) {

		{// sample, send the json by ajax from web page
			Map<String, Object> catMap = new HashMap<>();
			catMap.put("id", "");
//			catMap.put("catFriendName", "");
//			catMap.put("time", "");

			Map<String, Object> dogMap = new HashMap<>();
			dogMap.put("number", "");
			dogMap.put("userName", "");

			ro.getResultKeyMap().put("catTest", catMap);
			ro.getResultKeyMap().put("dogTest", dogMap);
		}


		String[] resultKeys = {
				"catTest.id",
				"catTest.catFriendName",
//				"dogTest.number",
				"dogTest.userName"
		};

		ro.setResultKeys(resultKeys);
//		ro.setScroll(true);

//		ro.setResultKeyMap();

		List<Object> inList = new ArrayList<>();
		inList.add("gggg");
		inList.add("xxxxx");
		ro.setOrderBy("catTest.catFriendName,catTest.id");

		Sort sort1 = new Sort();
		sort1.setOrderBy("catTest.catFriendName");
		sort1.setDirection(Direction.ASC);
		Sort sort2 = new Sort();
		sort2.setOrderBy("catTest.id");
		sort2.setDirection(Direction.DESC);
		List<Sort> sortList = new ArrayList<Sort>();
		sortList.add(sort1);
		sortList.add(sort2);

		ro.setOrderBy("catTest.catFriendName,catTest.id");
		ro.setDirection(Direction.DESC);
//		ro.setSortList(sortList);

		CriteriaBuilder.ResultMappedBuilder builder = CriteriaBuilder.buildResultMapped(CatTest.class,ro);
		//builder.distinct("catTest.id").reduce(Reduce.ReduceType.COUNT,"catTest.id").groupBy("catTest.id");
		builder.and().in("catTest.catFriendName", inList);
//		builder.paged().orderIn("catTest.catFriendName",inList);//按IN查询�?�件排�?，有值，就过滤掉orderBy
		String sourceScript = "catTest LEFT JOIN dogTest on catTest.dogId = dogTest.id";
		Criteria.ResultMappedCriteria resultMapped = builder.get();
		resultMapped.setSourceScript(sourceScript);
		Page<Map<String,Object>> page = repository.find(resultMapped);

//		Cat cat = this.catRepository.get(110);
//
//		System.out.println("____cat: " + cat);
//
//		List<Long> idList = new ArrayList<>();
//		idList.add(109L);
//		idList.add(110L);
//		InCondition inCondition = new InCondition("id",idList);
//		List<Cat> catList = this.catRepository.in(inCondition);

//		System.out.println("____catList: " + catList);

		return ViewEntity.ok(page);

	}



	@RequestMapping("/testOne")
	public ViewEntity testOne(@RequestBody CatRO ro) {


		String[] resultKeys = {
				"id",
				"type"
		};
//		ro.setOrderBy("cat.dogId");

		ro.setResultKeys(resultKeys);

		List<Object> inList = new ArrayList<>();
		inList.add("NL");
		inList.add("BL");

		CriteriaBuilder.ResultMappedBuilder builder = CriteriaBuilder.buildResultMapped(Cat.class,ro);
		builder.distinct("id").reduce(Reduce.ReduceType.COUNT,"dogId").groupBy("id");
		builder.and().in("type", inList);
		builder.paged().orderIn("type",inList);


//		builder.or().beginSub().eq("dogTest.userName","yyy")
//				.or().like("dogTest.userName",null)
//				.or().likeRight("dogTest.userName", "xxx")
//				.endSub();
//		builder.or().beginSub().eq("dogTest.userName", "uuu").endSub();


//		String sourceScript = "cat";

		Criteria.ResultMappedCriteria resultMapped = builder.get();

		Page<Map<String,Object>> pagination = repository.find(resultMapped);


		return ViewEntity.ok(pagination);

	}


	public ViewEntity nonPaged(@RequestBody CatRO ro) {

//		CriteriaBuilder.ResultMappedBuilder builder = CriteriaBuilder.buildResultMapped(Cat.class);
		CriteriaBuilder builder = CriteriaBuilder.build(Cat.class);

//		builder.resultKey("id").resultKey("type");
		List<Object> inList = new ArrayList<>();
		inList.add("BL");
		inList.add("NL");
		builder.and().in("type",inList);
		builder.paged().orderIn("type",inList);

//		Criteria.ResultMappedCriteria criteria = builder.get();
		Criteria criteria = builder.get();
		Page p = catRepository.find(criteria);

		return ViewEntity.ok(p);
	}


	@RequestMapping("/domain")
    public ViewEntity domain() {

		List<Long> catIdList = new ArrayList<>();
		catIdList.add(2L);
		catIdList.add(3L);

	    CriteriaBuilder.DomainObjectBuilder builder = CriteriaBuilder.buildDomainObject(Cat.class, Mouse.class);
		//根�?��?��?�?�件，例如ID，查出主对象
	    builder.and().in("id",catIdList);
	    //查出多对多关系的对象
	    builder.domain().relative(CatMouse.class).on("catId").with("mouseId");
	    //已知主对象，根�?�主对象查出多对多关系的对象
//		builder.domain().known(catIdList).relative(CatMouse.class).on("catId").with("mouseId");
	    Criteria.DomainObjectCriteria criteria = builder.get();

	    //多对多关系查询，仅�?于�?�一域下的对象, 必须�?�守�?��?�领域的设计
	    List<DomainObject<Cat,Mouse>> list = this.catRepository.listDomainObject(criteria);

	    String str = JsonX.toJson(list);//测试�?列化
		List<DomainObject> test = JsonX.toList(str,DomainObject.class);//测试�??�?列化
	    System.out.println(test);

	    return ViewEntity.ok(list);
    }



	@RequestMapping(value = "/reyc/test")
	public Boolean testRecClient() {

		try {
			TimeUnit.HOURS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return true;
	}

	@RequestMapping(value = "/get")
	public ViewEntity get() {

//		try {
//			TimeUnit.HOURS.sleep(1);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		return ViewEntity.ok(true);
	}

	@RequestMapping(value = "/time/test", method = RequestMethod.GET)
	public ViewEntity testTime() {

		Date date = new Date();
		System.out.println(date);

		TimeJack tj = new TimeJack();

		tj.setId(100);
		tj.setName("XXXXXX");
		tj.setDate(new Date());

		this.timeJackRepository.create(tj);

		List<TimeJack> list = this.timeJackRepository.list();

		return ViewEntity.ok(tj);
	}


	@RequestMapping(value = "/pig/get/{id}", method = RequestMethod.GET)
	public Pig getPig(@PathVariable long id) {

		Pig pig = this.pigRepository.get(id);

		System.out.println(pig);
		return pig;
	}

	@RequestMapping("/reyc/base")
	public int getBase(){
//		return "x7-demo/xxx/reyc/str";
		return 10;
	}

	@RequestMapping("/criteria/test")
	public ViewEntity testCtriteria(@RequestBody Criteria criteria){
		return ViewEntity.ok(criteria);
	}

	@RequestMapping("/resultmap/test")
	public ViewEntity testResultMap(@RequestBody Criteria.ResultMappedCriteria criteria){
		return ViewEntity.ok(criteria);
	}

	@RequestMapping("/domain/test")
	public ViewEntity testDomain(@RequestBody Criteria.DomainObjectCriteria criteria){
		return ViewEntity.ok(criteria);
	}

	@RequestMapping("/refreshCondition/test")
	public ViewEntity testRefreshConditionn(@RequestBody RefreshCondition refreshCondition){
		return ViewEntity.ok(refreshCondition);
	}

}
