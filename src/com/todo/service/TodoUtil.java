package com.todo.service;

import java.io.Writer;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

public class TodoUtil {
	
	public static void createItem(TodoList list) {
		
		String title, desc;
		Scanner sc = new Scanner(System.in);
		Scanner sd = new Scanner(System.in);
		
		System.out.print("[�� �� �߰�]"
				+ "\n���� > ");
		
		title = sc.next().trim();
		if (list.isDuplicate(title)) {
			System.out.println("- ������ ������ ���� �ֽ��ϴ� !");
			return;
		}
		
		System.out.print("���� > ");
		desc = sd.nextLine().trim();
		
		TodoItem t = new TodoItem(title, desc);
		list.addItem(t);
	}

	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("[�� �� ����]"
				+ "\n������ ���� ���� > ");
		
		String title = sc.next().trim();
		
		for (TodoItem item : l.getList()) {
			if (title.equals(item.getTitle())) {
				l.deleteItem(item);
				break;
			}
		}
	}


	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		Scanner sd = new Scanner(System.in);
		
		System.out.print("[�� �� ����]"
				+ "\n������ ���� ���� > ");
		
		String title = sc.next().trim();
		if (!l.isDuplicate(title)) {
			System.out.println("- �ش� ������ ���� �������� �ʽ��ϴ� !");
			return;
		}

		System.out.print("���ο� ���� ���� > ");
		String new_title = sc.next().trim();
		if (l.isDuplicate(new_title)) {
			System.out.println("- ������ ������ ���� �ֽ��ϴ� !");
			return;
		}
		
		System.out.print("���ο� ���� ���� > ");
		String new_description = sd.nextLine().trim();
		for (TodoItem item : l.getList()) {
			if (item.getTitle().equals(title)) {
				l.deleteItem(item);
				TodoItem t = new TodoItem(new_title, new_description);
				l.addItem(t);
				System.out.println("�����Ǿ����ϴ�.");
			}
		}

	}

	public static void listAll(TodoList l) {
		System.out.println("[�� �� ���]");
		for (TodoItem item : l.getList()) {
			System.out.printf("[%s] %s - %s\n", item.getTitle(), item.getDesc(), item.getCurrent_date());
		}
	}
	
	public static void saveList(TodoList l, String filename) {
		try {
			Writer w = new FileWriter(filename);
			
			for (TodoItem item : l.getList()) {
				w.write(item.toSaveString());
			}
			w.close();
			
			System.out.println("��� �����Ͱ� ����Ǿ����ϴ�.");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void loadList(TodoList l, String filename) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			
			String todoLine;
			while((todoLine = br.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(todoLine, "##");
				String title = st.nextToken();
				String desc = st.nextToken();
				String date = st.nextToken();
				
				TodoItem t = new TodoItem(title, desc, date);
				l.addItem(t);
			}
			br.close();
			System.out.println("�� �� ���� �ε� �Ϸ� !");
		} catch (FileNotFoundException e) {
			System.out.println(filename+" ������ �����ϴ�.");
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
}
