/**
 * Copyright (c) 2018-2028, Chill Zhuang åº„éªž (smallchill@163.com).
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
package org.springblade.system.controller;

import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import org.springblade.core.boot.ctrl.BladeController;
import org.springblade.core.mp.support.Condition;
import org.springblade.core.secure.BladeUser;
import org.springblade.core.secure.annotation.PreAuth;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.constant.RoleConstant;
import org.springblade.core.tool.support.Kv;
import org.springblade.core.tool.utils.Func;
import org.springblade.system.entity.Menu;
import org.springblade.system.service.IMenuService;
import org.springblade.system.vo.MenuVO;
import org.springblade.system.wrapper.MenuWrapper;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * æŽ§åˆ¶å™¨
 *
 * @author Chill
 */
@RestController
@AllArgsConstructor
@RequestMapping("/menu")
@Api(value = "è?œå?•", tags = "è?œå?•")
public class MenuController extends BladeController {

	private IMenuService menuService;

	/**
	 * è¯¦æƒ…
	 */
	@GetMapping("/detail")
	@PreAuth(RoleConstant.HAS_ROLE_ADMIN)
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "è¯¦æƒ…", notes = "ä¼ å…¥menu")
	public R<MenuVO> detail(Menu menu) {
		Menu detail = menuService.getOne(Condition.getQueryWrapper(menu));
		return R.data(MenuWrapper.build().entityVO(detail));
	}

	/**
	 * åˆ—è¡¨
	 */
	@GetMapping("/list")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "code", value = "è?œå?•ç¼–å?·", paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "name", value = "è?œå?•å??ç§°", paramType = "query", dataType = "string")
	})
	@PreAuth(RoleConstant.HAS_ROLE_ADMIN)
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "åˆ—è¡¨", notes = "ä¼ å…¥menu")
	public R<List<MenuVO>> list(@ApiIgnore @RequestParam Map<String, Object> menu) {
		@SuppressWarnings("unchecked")
		List<Menu> list = menuService.list(Condition.getQueryWrapper(menu, Menu.class).lambda().orderByAsc(Menu::getSort));
		return R.data(MenuWrapper.build().listNodeVO(list));
	}

	/**
	 * æ–°å¢žæˆ–ä¿®æ”¹
	 */
	@PostMapping("/submit")
	@PreAuth(RoleConstant.HAS_ROLE_ADMIN)
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "æ–°å¢žæˆ–ä¿®æ”¹", notes = "ä¼ å…¥menu")
	public R submit(@Valid @RequestBody Menu menu) {
		return R.status(menuService.saveOrUpdate(menu));
	}


	/**
	 * åˆ é™¤
	 */
	@PostMapping("/remove")
	@PreAuth(RoleConstant.HAS_ROLE_ADMIN)
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "åˆ é™¤", notes = "ä¼ å…¥ids")
	public R remove(@ApiParam(value = "ä¸»é”®é›†å?ˆ", required = true) @RequestParam String ids) {
		return R.status(menuService.removeByIds(Func.toIntList(ids)));
	}

	/**
	 * å‰?ç«¯è?œå?•æ•°æ?®
	 */
	@GetMapping("/routes")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "å‰?ç«¯è?œå?•æ•°æ?®", notes = "å‰?ç«¯è?œå?•æ•°æ?®")
	public R<List<MenuVO>> routes(BladeUser user) {
		List<MenuVO> list = menuService.routes(user.getRoleId());
		return R.data(list);
	}

	/**
	 * å‰?ç«¯æŒ‰é’®æ•°æ?®
	 */
	@GetMapping("/buttons")
	@ApiOperationSupport(order = 6)
	@ApiOperation(value = "å‰?ç«¯æŒ‰é’®æ•°æ?®", notes = "å‰?ç«¯æŒ‰é’®æ•°æ?®")
	public R<List<MenuVO>> buttons(BladeUser user) {
		List<MenuVO> list = menuService.buttons(user.getRoleId());
		return R.data(list);
	}

	/**
	 * èŽ·å?–è?œå?•æ ‘å½¢ç»“æž„
	 */
	@GetMapping("/tree")
	@ApiOperationSupport(order = 7)
	@ApiOperation(value = "æ ‘å½¢ç»“æž„", notes = "æ ‘å½¢ç»“æž„")
	public R<List<MenuVO>> tree() {
		List<MenuVO> tree = menuService.tree();
		return R.data(tree);
	}

	/**
	 * èŽ·å?–æ?ƒé™?åˆ†é…?æ ‘å½¢ç»“æž„
	 */
	@GetMapping("/grant-tree")
	@ApiOperationSupport(order = 8)
	@ApiOperation(value = "æ?ƒé™?åˆ†é…?æ ‘å½¢ç»“æž„", notes = "æ?ƒé™?åˆ†é…?æ ‘å½¢ç»“æž„")
	public R<List<MenuVO>> grantTree(BladeUser user) {
		return R.data(menuService.grantTree(user));
	}

	/**
	 * èŽ·å?–æ?ƒé™?åˆ†é…?æ ‘å½¢ç»“æž„
	 */
	@GetMapping("/role-tree-keys")
	@ApiOperationSupport(order = 9)
	@ApiOperation(value = "è§’è‰²æ‰€åˆ†é…?çš„æ ‘", notes = "è§’è‰²æ‰€åˆ†é…?çš„æ ‘")
	public R<List<String>> roleTreeKeys(String roleIds) {
		return R.data(menuService.roleTreeKeys(roleIds));
	}

	/**
	 * èŽ·å?–é…?ç½®çš„è§’è‰²æ?ƒé™?
	 */
	@GetMapping("auth-routes")
	@ApiOperationSupport(order = 10)
	@ApiOperation(value = "è?œå?•çš„è§’è‰²æ?ƒé™?")
	public R<List<Kv>> authRoutes(BladeUser user) {
		return R.data(menuService.authRoutes(user));
	}

}
