package org.weicong.kafka;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.TreeSet;

/**
 * @author weicong
 * @time   2020年12月30日
 * @version 1.0
 */
public class MainTest {
	
	public static void main(String[] args) {
//		User user = new User();
//		user.setId(1L);
//		User user2 = user;
//		System.out.println(user2.getId());
//		user.setId(2L);
//		System.out.println(user2.getId());
//		user2.setId(3L);
//		System.out.println(user.getId());
		
		// 数组
		String[] strings = new String[100000];
		for (int i=0;i< 99999;i++)
			strings[i] = i + "";
		long start1 = System.currentTimeMillis();
		for (int i = 0, len = strings.length; i < len; i++) {
//			"89999".equals(strings[i]);
			Arrays.binarySearch(strings, "89999");
		}
		long end1 = System.currentTimeMillis();
		
		//HashSet效率
		HashSet<String> hs = new HashSet();
		for (int i=0;i<99999;i++)
			hs.add(i + "");
		Iterator it = hs.iterator();
		long ks = System.currentTimeMillis();
		for (int i = 0; i < 100000; i++) {
			hs.contains("89999");
		}
		long js = System.currentTimeMillis();
		
		//LinkedHashSet效率
		LinkedHashSet<String> lh = new LinkedHashSet();
		for (int i=0;i<99999;i++)
			lh.add(i + "");
		Iterator it1 = lh.iterator();
		long ks1 = System.currentTimeMillis();
		for (int i = 0; i < 100000; i++) {
			lh.contains("89999");
		}
		long js1 = System.currentTimeMillis();
		
		//TreeSet效率
		TreeSet<String> ts = new TreeSet();
		for (int i=0;i<99999;i++)
			ts.add(i + "");
		Iterator it2 = ts.iterator();
		long ks2 = System.currentTimeMillis();
		for (int i = 0; i < 100000; i++) {
			ts.contains("89999");
		}
		long js2 = System.currentTimeMillis();
		System.out.println("HashSet共花费时间："+(js-ks)+"ms");
		System.out.println("LinkedHashSet共花费时间："+(js1-ks1)+"ms");
		System.out.println("TreeSet共花费时间："+(js2-ks2)+"ms");
		System.out.println("数组循环共花费时间："+(end1-start1)+"ms");
	}

	
}
