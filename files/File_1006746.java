package org.javacore.collection.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/*
 * Copyright [2015] [Jeff Lee]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * @author Jeff Lee
 * @since 2015-6-18 21:36:04
 * 	Iterator与ListIterator的区别
 */
public class IterAndListIterT {
	
	public static void main(String[] args) {
		//iterator();
		listIterator();
	}
	
	/**
	 * 并�?�修改异常产生。
	 * 原因：Iterator(Object obj = it.next()) 会检查HashMap的size，
	 * size�?�生�?�化，抛出错误ConcurrentModificationException。
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void iterator(){
		List a1 = new ArrayList<String>();
		a1.add("List01");
		a1.add("List02");
		a1.add("List04");
		a1.add("List05");
		
		Iterator i1 = a1.iterator();
		while (i1.hasNext()){
			Object obj = i1.next();
			if (obj.equals("List02"))
				a1.add("List03");
		}
		
		System.out.print("集�?�：\n\t"+a1+"\n");
	}
	
	/**
	 * ListIterator�?�以实现对象的修改。
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void listIterator(){
		List a1 = new ArrayList<String>();
		a1.add("List01");
		a1.add("List");
		a1.add("List03");
		a1.add("List04");
		
		ListIterator l1 = a1.listIterator();
		while (l1.hasNext()){
			Object obj = l1.next();
			if (obj.equals("List")){
				l1.remove();
				l1.add("List02");
			}
		}
		System.out.print("集�?�：\n\t"+a1+"\n");
	}
}
