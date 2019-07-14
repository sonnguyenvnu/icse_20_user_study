/*
 *
 *  * Copyright 2019 http://www.hswebframework.org
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *     http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package org.hswebframework.web.commons.entity;


import org.hswebframework.web.id.IDGenerator;
import org.hswebframework.utils.RandomUtil;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface TreeSupportEntity<PK> extends GenericEntity<PK> {

    String id = "id";

    String path = "path";

    String parentId = "parentId";

    String getPath();

    void setPath(String path);

    PK getParentId();

    void setParentId(PK parentId);

    Integer getLevel();

    void setLevel(Integer level);

    <T extends TreeSupportEntity<PK>> List<T> getChildren();

    /**
     * 根�?�path获�?�父节点的path
     *
     * @param path path
     * @return 父节点path
     */
    static String getParentPath(String path) {
        if (path == null || path.length() < 4) {
            return null;
        }
        return path.substring(0, path.length() - 5);
    }

    static <T extends TreeSupportEntity> void forEach(Collection<T> list, Consumer<T> consumer) {
        list.forEach(node -> {
            consumer.accept(node);
            if (node.getChildren() != null) {
                forEach(node.getChildren(), consumer);
            }
        });
    }

    static <T extends TreeSupportEntity<PK>, PK> void expandTree2List(T parent, List<T> target, IDGenerator<PK> idGenerator) {
        expandTree2List(parent,target,idGenerator,null);
    }
        /**
         * 将树形结构转为列表结构，并填充对应的数�?�。<br>
         * 如树结构数�?�： {name:'父节点',children:[{name:'�?节点1'},{name:'�?节点2'}]}<br>
         * 解�?�?�:[{id:'id1',name:'父节点',path:'<b>aoSt</b>'},{id:'id2',name:'�?节点1',path:'<b>aoSt</b>-oS5a'},{id:'id3',name:'�?节点2',path:'<b>aoSt</b>-uGpM'}]
         *
         * @param parent      树结构的根节点
         * @param target      目标集�?�,转�?��?�的数�?�将直接添加({@link List#add(Object)})到这个集�?�.
         * @param <T>         继承{@link TreeSupportEntity}的类型
         * @param idGenerator ID生�?策略
         * @param <PK>        主键类型
         */
    static <T extends TreeSupportEntity<PK>, PK> void expandTree2List(T parent, List<T> target, IDGenerator<PK> idGenerator, BiConsumer<T, List<T>> childConsumer) {

        List<T> children = parent.getChildren();
        if(childConsumer!=null){
            childConsumer.accept(parent,new ArrayList<>());
        }
        target.add(parent);
        if (parent.getPath() == null) {
            parent.setPath(RandomUtil.randomChar(4));
            if (parent.getPath() != null) {
                parent.setLevel(parent.getPath().split("-").length);
            }
            if (parent instanceof SortSupportEntity) {
                Long index = ((SortSupportEntity) parent).getSortIndex();
                if (null == index) {
                    ((SortSupportEntity) parent).setSortIndex(1L);
                }
            }
        }
        if (children != null) {
            PK pid = parent.getId();
            if (pid == null) {
                pid = idGenerator.generate();
                parent.setId(pid);
            }
            for (int i = 0; i < children.size(); i++) {
                T child = children.get(i);
                if (child instanceof SortSupportEntity && parent instanceof SortSupportEntity) {
                    Long index = ((SortSupportEntity) parent).getSortIndex();
                    if (null == index) {
                        ((SortSupportEntity) parent).setSortIndex(index = 1L);
                    }
                    ((SortSupportEntity) child).setSortIndex(new BigDecimal(index + "0" + (i + 1)).longValue());
                }
                child.setParentId(pid);
                child.setPath(parent.getPath() + "-" + RandomUtil.randomChar(4));
                child.setLevel(child.getPath().split("-").length);

                expandTree2List(child, target, idGenerator,childConsumer);
            }
        }
    }

    /**
     * 集�?�转为树形结构,返回根节点集�?�
     *
     * @param dataList      需�?转�?�的集�?�
     * @param childConsumer 设置�?节点回调
     * @param <N>           树节点类型
     * @param <PK>          主键类型
     * @return 树形结构集�?�
     */
    static <N extends TreeSupportEntity<PK>, PK> List<N> list2tree(Collection<N> dataList, BiConsumer<N, List<N>> childConsumer) {
        return list2tree(dataList, childConsumer, (Function<TreeHelper<N, PK>, Predicate<N>>) predicate -> node -> node == null || predicate.getNode(node.getParentId()) == null);
    }

    static <N extends TreeSupportEntity<PK>, PK> List<N> list2tree(Collection<N> dataList,
                                                                   BiConsumer<N, List<N>> childConsumer,
                                                                   Predicate<N> rootNodePredicate) {
        return list2tree(dataList, childConsumer, (Function<TreeHelper<N, PK>, Predicate<N>>) predicate -> rootNodePredicate);
    }

    /**
     * 列表结构转为树结构,并返回根节点集�?�
     *
     * @param dataList          数�?�集�?�
     * @param childConsumer     �?节点消费接�?�,用于设置�?节点
     * @param predicateFunction 根节点判断函数,传入helper,获�?�一个判断是�?�为跟节点的函数
     * @param <N>               元素类型
     * @param <PK>              主键类型
     * @return 根节点集�?�
     */
    static <N extends TreeSupportEntity<PK>, PK> List<N> list2tree(final Collection<N> dataList,
                                                                   final BiConsumer<N, List<N>> childConsumer,
                                                                   final Function<TreeHelper<N, PK>, Predicate<N>> predicateFunction) {
        Objects.requireNonNull(dataList, "source list can not be null");
        Objects.requireNonNull(childConsumer, "child consumer can not be null");
        Objects.requireNonNull(predicateFunction, "root predicate function can not be null");

        Supplier<Stream<N>> streamSupplier = () -> dataList.size() < 1000 ? dataList.stream() : dataList.parallelStream();
        // id,node
        Map<PK, N> cache = new HashMap<>();
        // parentId,children
        Map<PK, List<N>> treeCache = streamSupplier.get()
                .peek(node -> cache.put(node.getId(), node))
                .collect(Collectors.groupingBy(TreeSupportEntity::getParentId));

        Predicate<N> rootNodePredicate = predicateFunction.apply(new TreeHelper<N, PK>() {
            @Override
            public List<N> getChildren(PK parentId) {
                return treeCache.get(parentId);
            }

            @Override
            public N getNode(PK id) {
                return cache.get(id);
            }
        });

        return streamSupplier.get()
                //设置�?个节点的�?节点
                .peek(node -> childConsumer.accept(node, treeCache.get(node.getId())))
                //获�?�根节点
                .filter(rootNodePredicate)
                .collect(Collectors.toList());
    }

    /**
     * 树结构Helper
     *
     * @param <T>  节点类型
     * @param <PK> 主键类型
     */
    interface TreeHelper<T, PK> {
        /**
         * 根�?�主键获�?��?节点
         *
         * @param parentId 节点ID
         * @return �?节点集�?�
         */
        List<T> getChildren(PK parentId);

        /**
         * 根�?�id获�?�节点
         *
         * @param id 节点ID
         * @return 节点
         */
        T getNode(PK id);
    }
}
