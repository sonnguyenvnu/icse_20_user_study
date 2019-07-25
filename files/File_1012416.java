/**
 * Copyright (c) 2018-2028, Chill Zhuang 庄骞 (smallchill@163.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springblade.desk.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import org.springblade.common.cache.CacheNames;
import org.springblade.core.boot.ctrl.BladeController;
import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.Func;
import org.springblade.desk.entity.Notice;
import org.springblade.desk.service.INoticeService;
import org.springblade.desk.vo.NoticeVO;
import org.springblade.desk.wrapper.NoticeWrapper;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 控制器
 *
 * @author Chill
 * @since 2018-09-29
 */
@RestController
@RequestMapping("notice")
@AllArgsConstructor
@Api(value = "用户�?�客", tags = "�?�客接�?�")
public class NoticeController extends BladeController implements CacheNames {

	private INoticeService noticeService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入notice")
	public R<NoticeVO> detail(Notice notice) {
		Notice detail = noticeService.getOne(Condition.getQueryWrapper(notice));
		return R.data(NoticeWrapper.build().entityVO(detail));
	}

	/**
	 * 分页
	 */
	@GetMapping("/list")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "category", value = "公告类型", paramType = "query", dataType = "integer"),
		@ApiImplicitParam(name = "title", value = "公告标题", paramType = "query", dataType = "string")
	})
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入notice")
	public R<IPage<NoticeVO>> list(@ApiIgnore @RequestParam Map<String, Object> notice, Query query) {
		IPage<Notice> pages = noticeService.page(Condition.getPage(query), Condition.getQueryWrapper(notice, Notice.class));
		return R.data(NoticeWrapper.build().pageVO(pages));
	}

	/**
	 * 新增
	 */
	@PostMapping("/save")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "新增", notes = "传入notice")
	public R save(@RequestBody Notice notice) {
		return R.status(noticeService.save(notice));
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "修改", notes = "传入notice")
	public R update(@RequestBody Notice notice) {
		return R.status(noticeService.updateById(notice));
	}

	/**
	 * 新增或修改
	 */
	@PostMapping("/submit")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "新增或修改", notes = "传入notice")
	public R submit(@RequestBody Notice notice) {
		return R.status(noticeService.saveOrUpdate(notice));
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 6)
	@ApiOperation(value = "逻辑删除", notes = "传入notice")
	public R remove(@ApiParam(value = "主键集�?�") @RequestParam String ids) {
		boolean temp = noticeService.deleteLogic(Func.toIntList(ids));
		return R.status(temp);
	}

	/**
	 * 获�?�消�?�
	 *
	 * @return
	 */
	@GetMapping("/notices")
	@ApiOperationSupport(order = 7)
	@ApiOperation(value = "消�?�", notes = "消�?�")
	public R notices() {
		List<Map<String, String>> list = new ArrayList<>();
		Map<String, String> map1 = new HashMap<>(16);
		map1.put("logo", "https://spring.io/img/homepage/icon-spring-framework.svg");
		map1.put("title", "SpringBoot");
		map1.put("description", "现在的web项目几乎都会用到spring框架，而�?使用spring难�?需�?�?置大�?的xml�?置文件，而 springboot的出现解   决了这一问题，一个项目甚至�?用部署到�?务器上直接开跑，真�?springboot所说：“just run�?。");
		map1.put("member", "Chill");
		map1.put("href", "http://spring.io/projects/spring-boot");
		list.add(map1);

		Map<String, String> map2 = new HashMap<>(16);
		map2.put("logo", "https://spring.io/img/homepage/icon-spring-cloud.svg");
		map2.put("title", "SpringCloud");
		map2.put("description", "SpringCloud是基于SpringBoot的一整套实现微�?务的框架。他�??供了微�?务开�?�所需的�?置管�?��?�?务�?�现�?断路器�?智能路由�?微代�?��?控制总线�?全局�?�?决策竞选�?分布�?会�?和集群状�?管�?�等组件。");
		map2.put("member", "Chill");
		map2.put("href", "http://spring.io/projects/spring-cloud");
		list.add(map2);

		Map<String, String> map3 = new HashMap<>(16);
		map3.put("logo", "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1546359961068&di=05ff9406e6675ca9a58a525a7e7950b9&imgtype=jpg&src=http%3A%2F%2Fimg0.imgtn.bdimg.com%2Fit%2Fu%3D575314515%2C4268715674%26fm%3D214%26gp%3D0.jpg");
		map3.put("title", "Mybatis");
		map3.put("description", "MyBatis 是一款优秀的�?久层框架，它支�?定制化 SQL�?存储过程以�?�高级映射。MyBatis �?��?了几乎所有的 JDBC 代�?和手动设置�?�数以�?�获�?�结果集。MyBatis �?�以使用简�?�的 XML 或注解�?��?置和映射原生信�?�，将接�?�和 Java 的 POJOs(Plain Old Java Objects,普通的 Java对象)映射�?数�?�库中的记录。");
		map3.put("member", "Chill");
		map3.put("href", "http://www.mybatis.org/mybatis-3/getting-started.html");
		list.add(map3);

		Map<String, String> map4 = new HashMap<>(16);
		map4.put("logo", "https://gw.alipayobjects.com/zos/rmsportal/kZzEzemZyKLKFsojXItE.png");
		map4.put("title", "React");
		map4.put("description", "React 起�?于 Facebook 的内部项目，因为该公�?�对市场上所有 JavaScript MVC 框架，都�?满�?，就决定自己写一套，用�?�架设Instagram 的网站。�?�出�?�以�?�，�?�现这套东西很好用，就在2013年5月开�?了。");
		map4.put("member", "Chill");
		map4.put("href", "https://reactjs.org/");
		list.add(map4);

		Map<String, String> map5 = new HashMap<>(16);
		map5.put("logo", "https://gw.alipayobjects.com/zos/rmsportal/dURIMkkrRFpPgTuzkwnB.png");
		map5.put("title", "Ant Design");
		map5.put("description", "蚂�?金�?体验技术部�?过大�?的项目实践和总结，沉淀出设计语言 Ant Design，这�?��?�?�纯�?�是设计原则�?控件规范和视觉尺寸，还�?套有�?端代�?实现方案。也就是说采用Ant Design�?�，UI设计和�?端界�?�研�?��?��?�步完�?，效率大大�??�?�。");
		map5.put("member", "Chill");
		map5.put("href", "https://ant.design/docs/spec/introduce-cn");
		list.add(map5);

		Map<String, String> map6 = new HashMap<>(16);
		map6.put("logo", "https://gw.alipayobjects.com/zos/rmsportal/sfjbOqnsXXJgNCjCzDBL.png");
		map6.put("title", "Ant Design Pro");
		map6.put("description", "Ant Design Pro 是一个�?业级开箱�?�用的中�?��?��?端/设计解决方案。符�?�阿里追求的'�?�?�的�?端+强大的中�?�'的�?想。");
		map6.put("member", "Chill");
		map6.put("href", "https://pro.ant.design");
		list.add(map6);

		return R.data(list);
	}

	/**
	 * 获�?�我的消�?�
	 *
	 * @return
	 */
	@GetMapping("/my-notices")
	@ApiOperation(value = "消�?�", notes = "消�?�")
	public R myNotices() {
		List<Map<String, String>> list = new ArrayList<>();
		Map<String, String> map1 = new HashMap<>(16);
		map1.put("id", "000000001");
		map1.put("avatar", "https://gw.alipayobjects.com/zos/rmsportal/ThXAXghbEsBCCSDihZxY.png");
		map1.put("title", "你收到了 14 份新周报");
		map1.put("datetime", "2018-08-09");
		map1.put("type", "notification");
		list.add(map1);

		Map<String, String> map2 = new HashMap<>(16);
		map2.put("id", "000000002");
		map2.put("avatar", "https://gw.alipayobjects.com/zos/rmsportal/OKJXDXrmkNshAMvwtvhu.png");
		map2.put("title", "你推�??的 曲妮妮 已通过第三轮�?�试");
		map2.put("datetime", "2018-08-08");
		map2.put("type", "notification");
		list.add(map2);


		Map<String, String> map3 = new HashMap<>(16);
		map3.put("id", "000000003");
		map3.put("avatar", "https://gw.alipayobjects.com/zos/rmsportal/fcHMVNCjPOsbUGdEduuv.jpeg");
		map3.put("title", "曲丽丽 评论了你");
		map3.put("description", "�??述信�?��??述信�?��??述信�?�");
		map3.put("datetime", "2018-08-07");
		map3.put("type", "message");
		map3.put("clickClose", "true");
		list.add(map3);


		Map<String, String> map4 = new HashMap<>(16);
		map4.put("id", "000000004");
		map4.put("avatar", "https://gw.alipayobjects.com/zos/rmsportal/fcHMVNCjPOsbUGdEduuv.jpeg");
		map4.put("title", "朱�??�?� 回�?了你");
		map4.put("description", "这�?模�?�用于�??醒�?与你�?�生了互动，左侧放『�?�?的头�?");
		map4.put("type", "message");
		map4.put("datetime", "2018-08-07");
		map4.put("clickClose", "true");
		list.add(map4);


		Map<String, String> map5 = new HashMap<>(16);
		map5.put("id", "000000005");
		map5.put("title", "任务�??称");
		map5.put("description", "任务需�?在 2018-01-12 20:00 �?�?�动");
		map5.put("extra", "未开始");
		map5.put("status", "todo");
		map5.put("type", "event");
		list.add(map5);

		return R.data(list);
	}

}
