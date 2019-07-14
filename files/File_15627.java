/*
 * Copyright 2019 http://www.hswebframework.org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package org.hswebframework.web.authorization;

import java.io.Serializable;
import java.util.*;

/**
 * ç”¨æˆ·æŽˆæ?ƒä¿¡æ?¯,å½“å‰?ç™»å½•ç”¨æˆ·çš„æ?ƒé™?ä¿¡æ?¯,åŒ…æ‹¬ç”¨æˆ·çš„åŸºæœ¬ä¿¡æ?¯,è§’è‰²,æ?ƒé™?é›†å?ˆç­‰å¸¸ç”¨ä¿¡æ?¯<br>
 * èŽ·å?–æ–¹å¼?:
 * <ul>
 * <li>springmvc å…¥å?‚æ–¹å¼?: ResponseMessage myTest(Authorization auth){}</li>
 * <li>é?™æ€?æ–¹æ³•æ–¹å¼?:AuthorizationHolder.get();</li>
 * </ul>
 *
 * @author zhouhao
 * @see AuthenticationHolder
 * @see AuthenticationManager
 * @since 3.0
 */
public interface Authentication extends Serializable {

    /**
     * èŽ·å?–å½“å‰?ç™»å½•çš„ç”¨æˆ·æ?ƒé™?ä¿¡æ?¯
     * <pre>
     *
     *   Authentication auth= Authentication.current().get();
     *   //å¦‚æžœæ?ƒé™?ä¿¡æ?¯ä¸?å­˜åœ¨å°†æŠ›å‡º{@link NoSuchElementException}å»ºè®®ä½¿ç”¨ä¸‹é?¢çš„æ–¹å¼?èŽ·å?–
     *   Authentication auth=Authentication.current().orElse(null);
     *   //æˆ–è€…
     *   Authentication auth=Authentication.current().orElseThrow(UnAuthorizedException::new);
     * </pre>
     *
     * @return è¿”å›žOptionalå¯¹è±¡è¿›è¡Œæ“?ä½œ
     * @see Optional
     * @see AuthenticationHolder
     */
    static Optional<Authentication> current() {
        return Optional.ofNullable(AuthenticationHolder.get());
    }

    /**
     * @return ç”¨æˆ·ä¿¡æ?¯
     */
    User getUser();

    /**
     * @return ç”¨æˆ·æŒ?æœ‰çš„è§’è‰²é›†å?ˆ
     */
    List<Role> getRoles();

    /**
     * @return ç”¨æˆ·æŒ?æœ‰çš„æ?ƒé™?é›†å?ˆ
     */
    List<Permission> getPermissions();

    /**
     * æ ¹æ?®idèŽ·å?–è§’è‰²,è§’è‰²ä¸?å­˜åœ¨åˆ™è¿”å›žnull
     *
     * @param id è§’è‰²id
     * @return è§’è‰²ä¿¡æ?¯
     */
    default Optional<Role> getRole(String id) {
        if (null == id) {
            return Optional.empty();
        }
        return getRoles().stream()
                .filter(role -> role.getId().equals(id))
                .findAny();
    }

    /**
     * æ ¹æ?®æ?ƒé™?idèŽ·å?–æ?ƒé™?ä¿¡æ?¯,æ?ƒé™?ä¸?å­˜åœ¨åˆ™è¿”å›žnull
     *
     * @param id æ?ƒé™?id
     * @return æ?ƒé™?ä¿¡æ?¯
     */
    default Optional<Permission> getPermission(String id) {
        if (null == id) {
            return Optional.empty();
        }
        return getPermissions().stream()
                .filter(permission -> permission.getId().equals(id))
                .findAny();
    }

    /**
     * åˆ¤æ–­æ˜¯å?¦æŒ?æœ‰æŸ?æ?ƒé™?ä»¥å?Šå¯¹æ?ƒé™?çš„å?¯æ“?ä½œäº‹ä»¶
     *
     * @param permissionId æ?ƒé™?id {@link Permission#getId()}
     * @param actions      å?¯æ“?ä½œäº‹ä»¶ {@link Permission#getActions()} å¦‚æžœä¸ºç©º,åˆ™ä¸?åˆ¤æ–­action,å?ªåˆ¤æ–­permissionId
     * @return æ˜¯å?¦æŒ?æœ‰æ?ƒé™?
     */
    default boolean hasPermission(String permissionId, String... actions) {
        return getPermission(permissionId)
                .filter(permission -> actions.length == 0 || permission.getActions().containsAll(Arrays.asList(actions)))
                .isPresent();
    }

    /**
     * @param roleId è§’è‰²id {@link Role#getId()}
     * @return æ˜¯å?¦æ‹¥æœ‰æŸ?ä¸ªè§’è‰²
     */
    default boolean hasRole(String roleId) {
        return getRole(roleId).isPresent();
    }

    /**
     * æ ¹æ?®å±žæ€§å??èŽ·å?–å±žæ€§å€¼,è¿”å›žä¸€ä¸ª{@link Optional}å¯¹è±¡ã€‚<br>
     * æ­¤æ–¹æ³•å?¯ç”¨äºŽèŽ·å?–è‡ªå®šä¹‰çš„å±žæ€§ä¿¡æ?¯
     *
     * @param name å±žæ€§å??
     * @param <T>  å±žæ€§å€¼ç±»åž‹
     * @return Optionalå±žæ€§å€¼
     */
    <T extends Serializable> Optional<T> getAttribute(String name);

    /**
     * è®¾ç½®ä¸€ä¸ªå±žæ€§å€¼,å¦‚æžœå±žæ€§å??ç§°å·²ç»?å­˜åœ¨,åˆ™å°†å…¶è¦†ç›–ã€‚<br>
     * æ³¨æ„?:ç”±äºŽæ?ƒé™?ä¿¡æ?¯å?¯èƒ½ä¼šè¢«åº?åˆ—åŒ–,å±žæ€§å€¼å¿…é¡»å®žçŽ°{@link Serializable}æŽ¥å?£
     *
     * @param name   å±žæ€§å??ç§°
     * @param object å±žæ€§å€¼
     * @see AuthenticationManager#sync(Authentication)
     */
    void setAttribute(String name, Serializable object);

    /**
     * è®¾ç½®å¤šä¸ªå±žæ€§å€¼,å?‚æ•°ä¸ºmapç±»åž‹,keyä¸ºå±žæ€§å??ç§°,valueä¸ºå±žæ€§å€¼
     *
     * @param attributes å±žæ€§å€¼map
     * @see AuthenticationManager#sync(Authentication)
     */
    void setAttributes(Map<String, Serializable> attributes);

    /**
     * åˆ é™¤å±žæ€§,å¹¶è¿”å›žè¢«åˆ é™¤çš„å€¼
     *
     * @param name å±žæ€§å??
     * @param <T>  è¢«åˆ é™¤çš„å€¼ç±»åž‹
     * @return è¢«åˆ é™¤çš„å€¼
     * @see AuthenticationManager#sync(Authentication)
     */
    <T extends Serializable> T removeAttributes(String name);

    /**
     * èŽ·å?–å…¨éƒ¨å±žæ€§,æ­¤å±žæ€§ä¸ºé€šè¿‡{@link this#setAttribute(String, Serializable)}æˆ–{@link this#setAttributes(Map)}è®¾ç½®çš„å±žæ€§ã€‚
     *
     * @return å…¨éƒ¨å±žæ€§é›†å?ˆ
     */
    Map<String, Serializable> getAttributes();

}
